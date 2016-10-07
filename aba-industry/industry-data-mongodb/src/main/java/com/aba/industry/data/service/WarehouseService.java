/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.data.service;

import com.aba.industry.domain.WarehouseItem;

import java.util.List;

/**
 * Created by maurerit on 9/30/16.
 */
public interface WarehouseService {
    /**
     * Administrative update method.  Consumption and purchases should instead flow through
     * {@link WarehouseService#consumedWarehouseItem(Long, Long)} or
     * {@link WarehouseService#purchasedWarehouseItem(Long, Long, Double)}
     *
     * @param typeId
     * @param quantityToAdd
     */
    void updateWarehouseItem ( Long typeId, Long quantityToAdd );

    /**
     * Administrative update method.  Consumption and purchases should instead flow through
     * {@link WarehouseService#consumedWarehouseItem(Long, Long)} or
     * {@link WarehouseService#purchasedWarehouseItem(Long, Long, Double)}
     *
     * @param typeId
     * @param quantityToAdd
     */
    void updateWarehouseItem ( Long typeId, Long quantityToAdd, Double costOfNewItems );

    void removeWarehouseItem ( Long typeId );

    void consumedWarehouseItem ( Long typeId, Long quantity );

    void purchasedWarehouseItem ( Long typeId, Long quantity, Double price );

    WarehouseItem getWarehouseItem ( Long typeId );

    List<WarehouseItem> getWarehouseItems ( );
}
