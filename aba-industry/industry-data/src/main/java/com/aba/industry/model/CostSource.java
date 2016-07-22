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
     * Buy prices from the live market (probably from CREST)
     */
    LIVE_MARKET_BUY,
    /**
     * Sell prices from the live market (probably from CREST)
     */
    LIVE_MARKET_SELL
}
