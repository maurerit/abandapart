package com.aba.industry.model;

import lombok.Data;

/**
 * Created by MM66053 on 1/23/2017.
 */
@Data
public class WarehouseResponse {
    private Integer typeId;
    private Long quantityInStock = 0l;
    private Double priceOfStock = 0d;
    private Long quantityFromMarket = 0l;
    private Double priceOfMarket = 0d;

    public Double calcPricePerEach( ) {
        Double pricePerEach = 0d;
        Double totalPrice;

        if ( quantityInStock > 0 && priceOfStock > 0 ) {
            totalPrice = quantityInStock * priceOfStock;
        }

        if ( quantityFromMarket > 0 && priceOfMarket > 0 ) {

        }

        return pricePerEach;
    }
}
