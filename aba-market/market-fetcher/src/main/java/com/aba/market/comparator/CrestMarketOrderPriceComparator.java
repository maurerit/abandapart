package com.aba.market.comparator;

import org.devfleet.crest.model.CrestMarketOrder;

import java.util.Comparator;

/**
 * Created by maurerit on 7/28/2016.
 */
public class CrestMarketOrderPriceComparator implements Comparator<CrestMarketOrder> {
    @Override
    public int compare ( CrestMarketOrder o1, CrestMarketOrder o2 )
    {
        if ( o1.getPrice() < o2.getPrice() ) {
            return -1;
        }
        else if ( o1.getPrice() > o2.getPrice() ) {
            return 1;
        }

        return 0;
    }
}
