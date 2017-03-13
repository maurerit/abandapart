/*
 * Copyright 2017 maurerit
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
import com.aba.market.fetch.MarketPriceFetcher;
import com.aba.market.fetch.MarketPriceSearcher;
import lombok.Setter;
import org.devfleet.crest.model.CrestMarketOrder;
import org.devfleet.crest.model.CrestMarketPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

/**
 * Created by maurerit on 9/7/16.
 */
@Service
@Setter
public class CrestMarketPriceSearcher implements MarketPriceSearcher {
    @Autowired
    private MarketOrderFetcher marketOrderFetcher;

    @Autowired
    private MarketPriceFetcher marketPriceFetcher;

    @Override
    @Cacheable( "lowest-sell-price" )
    public Double getLowestSellPrice ( long regionId, long systemId, long itemId )
    {
        Double result = null;

        //Used in the below lambda, needs to be final
        final Long hubIdToFind = TradeHubs.findBySystemId( systemId )
                                          .getStationId();

        List<CrestMarketOrder> sellOrders = marketOrderFetcher.getMarketSellOrders( regionId,
                                                                                    itemId );

        OptionalDouble priceOpt = sellOrders.stream()
                                            .filter(
                                                    order -> order.getLocationId() == hubIdToFind )
                                            .sorted(
                                                    new CrestMarketOrderPriceAscendingComparator
                                                            () )
                                            .mapToDouble( order -> order.getPrice() )
                                            .findFirst();

        if ( priceOpt.isPresent() ) {
            result = priceOpt.getAsDouble();
        }

        return result;
    }

    @Override
    @Cacheable( "adjusted-price" )
    public Double getAdjustedPrice ( long typeId ) {
        Double result = 0d;

        List<CrestMarketPrice> prices = marketPriceFetcher.getAllMarketPrices();

        OptionalDouble possibleResult = prices.stream()
                                              .filter( cmp -> cmp.getType()
                                                                 .getId() == typeId )
                                              .mapToDouble( cmp -> cmp.getAdjustedPrice() )
                                              .findFirst();

        if ( possibleResult.isPresent() ) {
            result = possibleResult.getAsDouble();
        }

        return result;
    }
}
