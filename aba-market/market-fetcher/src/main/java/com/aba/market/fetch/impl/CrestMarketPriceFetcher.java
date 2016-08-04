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

import com.aba.market.fetch.MarketPriceFetcher;
import lombok.Setter;
import org.devfleet.crest.CrestService;
import org.devfleet.crest.model.CrestMarketPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

/**
 * Created by maurerit on 7/29/16.
 */
@Service
@Setter
public class CrestMarketPriceFetcher implements MarketPriceFetcher {
    @Autowired
    private CrestService crestService;

    @Override
    @Cacheable( "adjusted-price" )
    public Double getAdjustedPrice ( long typeId ) {
        Double result = 0d;

        List<CrestMarketPrice> prices = this.getAllMarketPrices();

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

    @Override
    @Cacheable( "all-market-prices" )
    public List<CrestMarketPrice> getAllMarketPrices ( ) {
        return crestService.getAllMarketPrices();
    }
}
