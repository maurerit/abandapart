package com.aba.market.fetch.impl;

import com.aba.market.fetch.MarketOrderFetcher;
import lombok.Setter;
import org.devfleet.crest.CrestService;
import org.devfleet.crest.model.CrestMarketOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mm66053 on 7/26/2016.
 */
@Setter
public class CrestMarketOrderFetcher implements MarketOrderFetcher {
    @Autowired
    private CrestService crestService;

    @Override
    public List<CrestMarketOrder> getMarketSellOrders(long regionId, long itemId) {
        return crestService.getMarketOrders(regionId, "sell", itemId);
    }

    @Override
    public Double getLowestSellPrice(long regionId, long systemId, long itemId) {
        Double result = 0d;

        List<CrestMarketOrder> sellOrders = getMarketSellOrders( regionId, itemId );

//        result = sellOrders.stream().filter( order -> order.)

        return result;
    }
}
