/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the License.
 */

package com.aba.market.fetch.impl;

import com.aba.market.fetch.MarketOrderFetcher;
import lombok.Setter;
import org.devfleet.crest.CrestService;
import org.devfleet.crest.model.CrestMarketOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.OptionalDouble;

/**
 * Created by mm66053 on 7/26/2016.
 */
@Setter
public class CrestMarketOrderFetcher implements MarketOrderFetcher {
    @Autowired
    private CrestService crestService;

    @Override
    public List<CrestMarketOrder> getMarketSellOrders ( long regionId, long itemId ) {
        return crestService.getMarketOrders( regionId, "sell", itemId );
    }

    @Override
    public Double getLowestSellPrice ( long regionId, long systemId, long itemId ) {
        Double result = null;

        //TODO: For now, I'm concerned with Amarr and Jita, hard coding the hub stations id's
        Long jitaFourMoonFourId = 60003760l;
        Long amarrEightId = 60008494l;
        //Used in the below lambda, needs to be final
        final Long hubIdToFind = systemId == jitaFourMoonFourId ? jitaFourMoonFourId : amarrEightId;

        List<CrestMarketOrder> sellOrders = getMarketSellOrders( regionId, itemId );

        OptionalDouble price = sellOrders.stream()
                                         .filter( order -> order.getLocationId() == hubIdToFind )
                                         .mapToDouble( order -> order.getPrice() )
                                         .findFirst();

        if ( price.isPresent() ) {
            result = price.getAsDouble();
        }

        return result;
    }
}
