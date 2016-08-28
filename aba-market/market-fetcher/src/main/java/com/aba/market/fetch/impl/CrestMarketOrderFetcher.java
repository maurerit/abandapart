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

import com.aba.market.fetch.MarketOrderFetcher;
import lombok.Setter;
import org.devfleet.crest.CrestService;
import org.devfleet.crest.model.CrestMarketOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by maurerit on 7/26/2016.
 */
@Service
@Setter
public class CrestMarketOrderFetcher implements MarketOrderFetcher {
    private static final Logger logger = LoggerFactory.getLogger( CrestMarketOrderFetcher.class );
    @Autowired
    private CrestService crestService;

    @Override
    @Cacheable( "crest-sell-orders" )
    public List<CrestMarketOrder> getMarketSellOrders ( long regionId, long itemId )
    {
        logger.debug( "fetching sell prices in region: {}, for item: {}", regionId, itemId );
        return crestService.getMarketOrders( regionId, "sell", itemId );
    }

    @Override
    @Cacheable( "crest-buy-orders" )
    public List<CrestMarketOrder> getMarketBuyOrders ( long regionId, long itemId ) {
        logger.debug( "fetching buy prices in region: {}, for item: {}", regionId, itemId );
        return crestService.getMarketOrders( regionId, "buy", itemId );
    }

    //TODO: Figure out how to properly uncomment and utilize the below commented out method.  Initial tests do not
    // look good.
    //The below comment is a potential to do as well if some things are figured out. These things are how to get this
    // method
    // to cache without putting it into a linked service/component and how to properly cache it.  Maybe this does
    // belong into
    //another service/component just because it'll be handy to have for mass build calculation checks on a backend?
    //Candidate for inclusion into the implemented interface
//    @Cacheable( "all-market-orders" )
//    public List<CrestMarketBulkOrder> getAllMarketOrders ( long regionId ) {
//        return crestService.getAllMarketOrders( regionId );
//    }
}
