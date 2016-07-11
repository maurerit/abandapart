package com.aba.industry.model;

/**
 * @author maurerit
 */
public enum CostSource {
    /**
     * Prices of the current inventory
     */
    WAREHOUSE,
    /**
     * Prices from the live market (probably from CREST)
     */
    LIVE_MARKET
}
