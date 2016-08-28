/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.market.fetch.impl;

import com.aba.market.TradeHubs;
import com.aba.market.comparator.CrestMarketOrderPriceAscendingComparator;
import com.aba.market.fetch.MarketOrderFetcher;
import com.aba.market.fetch.MarketOrderSearcher;
import lombok.Setter;
import org.devfleet.crest.model.CrestMarketOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by maurerit on 8/27/16.
 */
@Service
@Setter
public class CrestMarketOrderSearcher implements MarketOrderSearcher {
    private static final Logger logger = LoggerFactory.getLogger( CrestMarketOrderSearcher.class );

    @Autowired
    private MarketOrderFetcher marketOrderFetcher;

    @Override
    @Cacheable( "price-for-quantity" )
    public Double getPriceForQuantity ( long regionId, long systemId, long itemId, long quantity )
    {
        logger.debug( "fetching price for quantity in region: {}, system: {}, for item: {} and quantity: {}", regionId,
                      systemId, itemId, quantity );
        Double result = 0d;

        final long hubIdToFind = TradeHubs.findBySystemId( systemId )
                                          .getStationId();

        List<CrestMarketOrder> sellOrders = marketOrderFetcher.getMarketSellOrders( regionId, itemId );

        List<CrestMarketOrder> filteredSellOrders = sellOrders.stream()
                                                              .filter(
                                                                      order -> order.getLocationId() ==
                                                                              hubIdToFind && order.getTypeId() ==
                                                                              itemId )
                                                              .sorted( new CrestMarketOrderPriceAscendingComparator() )
                                                              .collect( Collectors.toList() );

        int totalFound = 0;
        double totalPrice = 0d;

        for ( CrestMarketOrder crestMarketOrder : filteredSellOrders ) {
            if ( quantity > totalFound ) {
                totalFound += crestMarketOrder.getVolume();
                totalPrice += crestMarketOrder.getVolume() * crestMarketOrder.getPrice();
            }
            else if ( quantity <= totalFound ) {
                break;
            }
        }

        result = totalPrice / totalFound;

        return result;
    }
}
