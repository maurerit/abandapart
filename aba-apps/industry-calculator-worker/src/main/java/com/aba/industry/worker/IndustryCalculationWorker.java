/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.worker;

import com.aba.data.domain.config.BuildOrBuyConfiguration;
import com.aba.eveonline.repo.RegionRepository;
import com.aba.eveonline.repo.SolarSystemRepository;
import com.aba.industry.TradeHubs;
import com.aba.industry.bus.model.BuildCalculationRequest;
import com.aba.industry.config.BuildOrBuyConfigurationService;
import com.aba.industry.model.BuildCalculationResult;
import com.aba.industry.service.IndustryCalculationService;
import com.aba.market.comparator.CrestMarketOrderPriceAscendingComparator;
import com.aba.market.fetch.MarketOrderFetcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.devfleet.crest.model.CrestMarketOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.zeromq.ZMQ;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.*;

/**
 * Created by maurerit on 8/6/16.
 */
@Component
@ConfigurationProperties( prefix = "aba.industry.bus.worker" )
public class IndustryCalculationWorker implements Runnable {
    public static final Logger logger = LoggerFactory.getLogger( IndustryCalculationWorker.class );

    @Autowired
    private IndustryCalculationService calculationService;

    @Autowired
    private BuildOrBuyConfigurationService buildOrBuyConfigurationService;

    @Autowired
    private MarketOrderFetcher crestMarketOrderFetcher;

    @Autowired
    private SolarSystemRepository crestSolarSystemRepository;

    @Autowired
    private RegionRepository crestRegionRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Value( "${aba.industry.bus.worker.routerLocation}" )
    private String routerLocation;
    @Value( "${aba.industry.bus.worker.routerProtocol}" )
    private String routerProtocol;
    @Value( "${aba.industry.bus.worker.routerPort}" )
    private String routerPort;

    private String                       myName;
    private List<SolarSystemInformation> hubs;

    public void run ( ) {
        hubs = new ArrayList<>();

        for ( TradeHubs hub : TradeHubs.values() ) {
            SolarSystemInformation info = new SolarSystemInformation();

            info.setSystemName( hub.getSystemName() );
            info.setSystemId( crestSolarSystemRepository.getSolarSystemId( hub.getSystemName() ) );
            info.setRegionName( hub.getRegionName() );
            info.setRegionId( crestRegionRepository.findRegionId( hub.getRegionName() ) );
            info.setStationName( hub.getStationName() );
            info.setStationId( hub.getStationId() );

            hubs.add( info );
        }

        myName = ManagementFactory.getRuntimeMXBean()
                                  .getName();
        ZMQ.Context context = ZMQ.context( 1 );
        ZMQ.Socket worker = context.socket( ZMQ.REQ );
        worker.setIdentity( myName.getBytes( ZMQ.CHARSET ) );

        worker.connect( routerProtocol + "://" + routerLocation + ":" + routerPort );
        worker.send( "READY" );

        while ( !Thread.currentThread()
                       .isInterrupted() ) {
            String address = worker.recvStr();
            String empty = worker.recvStr();
            assert ( empty.length() == 0 );

            String request = worker.recvStr();

            String response = null;
            try {
                response = calculateBuild( request );
            }
            catch ( IOException e ) {
                logger.error( "Caught IOException while processing {}", request );
                logger.error( "Stacktrace", e );
            }

            worker.sendMore( address );
            worker.sendMore( "" );
            worker.send( response );
        }

        worker.close();
        context.term();
    }

    private String calculateBuild ( String requestString ) throws IOException {
        logger.debug( "{} Received a build calc request {}", myName, requestString );
        String result = null;
        BuildCalculationRequest request = mapper.readValue( requestString, BuildCalculationRequest.class );

        if ( request.getBuildOrBuyConfigurationList() != null ) {
            for ( BuildOrBuyConfiguration configuration : request.getBuildOrBuyConfigurationList() ) {
                buildOrBuyConfigurationService.createOrUpdateBuildOrBuyConfiguration( configuration );
            }
        }

        BuildCalculationResult resultObj = calculationService.calculateBuildCosts( request.getSystemName(),
                                                                                   request.getRequestedBuildTypeId(),
                                                                                   request.getIndustrySkills(),
                                                                                   request.getInventionSkills(),
                                                                                   request.getMeLevel(),
                                                                                   request.getTeLevel(),
                                                                                   request.getDecryptor(),
                                                                                   request.isFindPrices(),
                                                                                   request.isUseBuildOrBuyConfigurations() );

        populateHubPrices( resultObj );


        if ( request.getBuildOrBuyConfigurationList() != null ) {
            for ( BuildOrBuyConfiguration configuration : request.getBuildOrBuyConfigurationList() ) {
                buildOrBuyConfigurationService.deleteBuildOrByConfiguration( configuration );
            }
        }

        result = mapper.writeValueAsString( resultObj );

        return result;
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
                                     .sorted( new CrestMarketOrderPriceAscendingComparator() )
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
}
