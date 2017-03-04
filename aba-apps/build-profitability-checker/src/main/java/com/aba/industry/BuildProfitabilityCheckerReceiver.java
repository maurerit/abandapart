/*
 * Copyright 2017 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry;

import com.aba.eveonline.repo.ItemTypeRepository;
import com.aba.eveonline.repo.RegionRepository;
import com.aba.eveonline.repo.SolarSystemRepository;
import com.aba.industry.model.ActivityMaterialWithCost;
import com.aba.industry.model.BuildCalculationResult;
import com.aba.industry.worker.SolarSystemInformation;
import com.aba.market.TradeHubs;
import com.aba.market.comparator.CrestMarketOrderPriceAscendingComparator;
import com.aba.market.comparator.CrestMarketOrderPriceDescendingComparator;
import com.aba.market.fetch.MarketOrderFetcher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.devfleet.crest.model.CrestMarketOrder;
import org.devfleet.crest.model.CrestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.*;

/**
 * Created by maurerit on 1/27/17.
 */
@Component
public class BuildProfitabilityCheckerReceiver implements InitializingBean {
    private static final Logger       logger = LoggerFactory.getLogger( BuildProfitabilityCheckerReceiver.class );
    private              ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    @Autowired
    private SolarSystemRepository crestSolarSystemRepository;

    @Autowired
    private MarketOrderFetcher crestMarketOrderFetcher;

    @Autowired
    private RegionRepository crestRegionRepository;

    private List<SolarSystemInformation> hubs;

    public void handleMessage ( BuildCalculationResult message ) throws JsonProcessingException {
        CrestType type = itemTypeRepository.getItemDetails( message.getProductId() );
        if ( type != null ) {
            message.setProductName( type.getName() );

            populateActivityCostItemNames( message );
            populateHubPrices( message );

            logger.debug( mapper.writeValueAsString( message ) );
            if ( productProfitability( message ) > 2 ) {
                logger.info( printHubPofitabilities( message ) );
            }
        }
    }

    private void populateActivityCostItemNames ( BuildCalculationResult message ) {
        for ( ActivityMaterialWithCost amwc : message.getMaterialsWithCost() ) {
            CrestType type = itemTypeRepository.getItemDetails( amwc.getTypeId() );
            if ( type != null ) {
                amwc.setName( type.getName() );
            }
        }

        if ( message.getInventionResult() != null ) {
            for ( ActivityMaterialWithCost amwc : message.getInventionResult()
                                                         .getMaterialsWithCost() ) {
                CrestType type = itemTypeRepository.getItemDetails( amwc.getTypeId() );
                if ( type != null ) {
                    amwc.setName( type.getName() );
                }
            }
        }
    }

    //TODO: Candidate for promotion to something else maybe.  Idea's are to potentially make this another microservice
    private void populateHubPrices ( BuildCalculationResult resultObj ) {
        //TODO: #33 check Amarr, Rens, Dodixie and Jita for prices of the product and put it into the result
        for ( SolarSystemInformation info : hubs ) {
            //region Sell Orders
            List<CrestMarketOrder> sellOrders = crestMarketOrderFetcher.getMarketSellOrders( info.getRegionId(),
                                                                                             resultObj.getProductId() );

            Optional<CrestMarketOrder> orderOptional = sellOrders.stream()
                                                                 .filter(
                                                                         order -> order.getLocationId() == info
                                                                                 .getStationId() )
                                                                 .sorted(
                                                                         new CrestMarketOrderPriceAscendingComparator
                                                                                 () )
                                                                 .findFirst();

            if ( orderOptional.isPresent() ) {
                Map<String, CrestMarketOrder> map = resultObj.getLowestSellOrders();
                if ( map == null ) {
                    map = new HashMap<>();
                    resultObj.setLowestSellOrders( map );
                }
                map.put( info.getSystemName(), orderOptional.get() );
            }
            //endregion

            //region Buy Orders
            List<CrestMarketOrder> buyOrders = crestMarketOrderFetcher.getMarketBuyOrders( info.getRegionId(),
                                                                                           resultObj.getProductId() );

            orderOptional = buyOrders.stream()
                                     .filter( order -> order.getLocationId() == info.getStationId() )
                                     .sorted( new CrestMarketOrderPriceDescendingComparator() )
                                     .findFirst();

            if ( orderOptional.isPresent() ) {
                Map<String, CrestMarketOrder> map = resultObj.getHighestBuyOrders();
                if ( map == null ) {
                    map = new HashMap<>();
                    resultObj.setHighestBuyOrders( map );
                }
                map.put( info.getSystemName(), orderOptional.get() );
            }
            //endregion
        }
    }

    private int productProfitability ( BuildCalculationResult message ) {
        int result = 0;

        if ( message.getLowestSellOrders() != null ) {
            for ( Map.Entry<String, CrestMarketOrder> entry : message.getLowestSellOrders()
                                                                     .entrySet() ) {
                if ( entry.getValue()
                          .getPrice() > message.getTotalCost() )
                {
                    result++;
                }
            }
        }
        else {
            result += hubs.size();
        }

        return result;
    }

    private String printHubPofitabilities ( BuildCalculationResult message ) {
        StringBuilder result = new StringBuilder();

        Format format = new DecimalFormat();

        result.append( "\n" )
              .append( message.getProductName() )
              .append( "\n\tbuild:" )
              .append( format.format( message.getTotalCost() ) );

        if ( message.getLowestSellOrders() != null ) {
            for ( Map.Entry<String, CrestMarketOrder> entry : message.getLowestSellOrders()
                                                                     .entrySet() ) {
                result.append( "\n\t" )
                      .append( entry.getKey() )
                      .append( ":sell:" )
                      .append( format.format( entry.getValue()
                                                   .getPrice() ) )
                      .append( ":difference:" )
                      .append( format.format( message.getTotalCost() - entry.getValue()
                                                                            .getPrice() ) );
            }
        }
        else {
            result.append( "\n\tNO SELL ORDERS" );
        }

        return result.toString();
    }

    @Override
    public void afterPropertiesSet ( ) throws Exception {
        hubs = new ArrayList<>();

        for ( TradeHubs hub : TradeHubs.values() ) {
            if ( hub == TradeHubs.NULL ) {
                continue;
            }
            SolarSystemInformation info = new SolarSystemInformation();

            info.setSystemName( hub.getSystemName() );
            info.setSystemId( crestSolarSystemRepository.getSolarSystemId( hub.getSystemName() ) );
            info.setRegionName( hub.getRegionName() );
            info.setRegionId( crestRegionRepository.findRegionId( hub.getRegionName() ) );
            info.setStationName( hub.getStationName() );
            info.setStationId( hub.getStationId() );

            hubs.add( info );
        }
    }
}
