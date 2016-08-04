/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.market.comparator;

import org.devfleet.crest.model.CrestMarketOrder;

import java.util.Comparator;

/**
 * Created by mm66053 on 8/3/2016.
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
