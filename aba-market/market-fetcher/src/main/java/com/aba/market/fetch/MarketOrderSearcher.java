/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.market.fetch;

/**
 * Created by maurerit on 8/27/16.
 */
//TODO: Find a better name for this.  Its a fetcher but it does something above and beyond JUST fetching
public interface MarketOrderSearcher {
    /**
     * Implementations of this method will inspect the market and report the amount per item that is need to purchase
     * the number specified by the {@param quantity} parameter.
     *
     * @param regionId
     * @param systemId
     * @param itemId
     * @param quantity
     * @return
     */
    Double getPriceForQuantity ( long regionId, long systemId, long itemId, long quantity );
}
