/*
 * Copyright 2017 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.data.service.impl;

import com.aba.industry.data.repo.WarehouseItemRepository;
import com.aba.industry.domain.WarehouseItem;
import com.aba.industry.data.service.WarehouseService;
import com.aba.industry.model.WarehouseResponse;
import com.aba.market.fetch.MarketOrderSearcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by maurerit on 10/2/16.
 */
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private MarketOrderSearcher marketOrderSearcher;

    @Autowired
    private WarehouseItemRepository warehouseItemRepository;


    @Override
    public void updateWarehouseItem ( Long typeId, Long quantityToAdd ) {

    }

    @Override
    public void updateWarehouseItem ( Long typeId, Long quantityToAdd, Double costOfNewItems ) {

    }

    @Override
    public void removeWarehouseItem ( Long typeId ) {

    }

    @Override
    public void consumedWarehouseItem ( Long typeId, Long quantity ) {

    }

    @Override
    public void purchasedWarehouseItem ( Long typeId, Long quantity, Double price ) {

    }

    @Override
    public boolean hasWarehouse ( Long entityId ) {
        return false;
    }

    @Override
    public WarehouseItem getWarehouseItem ( Long typeId ) {
        return null;
    }

    @Override
    public List<WarehouseItem> getWarehouseItems ( ) {
        return null;
    }

    @Override
    public WarehouseResponse getPriceForQuantity ( Long entityId, long regionId, long systemId, long itemId,
                                                   long quantity )
    {
        WarehouseResponse response = new WarehouseResponse();

        WarehouseItem warehouseItem = warehouseItemRepository.findByTypeIdAndEntityId( itemId, entityId );

        if ( warehouseItem != null ) {
            response.setPriceOfStock( warehouseItem.getCost() );
            response.setQuantityInStock( warehouseItem.getQuantity() );
        }

        if ( response.getQuantityInStock() < quantity ) {
            Double marketPrice = marketOrderSearcher.getPriceForQuantity( regionId, systemId, itemId,
                                                                          quantity - response.getQuantityInStock() );
            response.setPriceOfMarket( marketPrice );
            response.setQuantityFromMarket( quantity - response.getQuantityInStock() );
        }

        return response;
    }
}
