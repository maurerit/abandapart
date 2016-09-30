package com.aba.market.fetch.impl;

import com.aba.market.fetch.BulkMarketOrderFetcher;
import lombok.Getter;
import org.devfleet.crest.model.CrestMarketBulkOrder;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by maurerit on 9/7/2016.
 */
public class CrestBulkMarketOrderFetcher extends CrestMarketOrderFetcher implements BulkMarketOrderFetcher {
    @Override
    @Cacheable( "all-market-orders" )
    public List<CrestMarketBulkOrder> getAllMarketOrders ( long regionId ) {
        return crestService.getAllMarketOrders( regionId );
    }
}
