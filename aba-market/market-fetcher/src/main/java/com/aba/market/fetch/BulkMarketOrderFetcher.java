package com.aba.market.fetch;

import org.devfleet.crest.model.CrestMarketBulkOrder;

import java.util.List;

/**
 * Created by maurerit on 9/7/2016.
 */
public interface BulkMarketOrderFetcher extends MarketOrderFetcher {
    List<CrestMarketBulkOrder> getAllMarketOrders ( long regionId );
}
