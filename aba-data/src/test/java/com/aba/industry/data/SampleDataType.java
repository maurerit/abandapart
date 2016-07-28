/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.data;

import com.aba.data.TypedJsonMessage;

import java.util.Map;

public class SampleDataType extends TypedJsonMessage {
    private String              id;
    private int                 quantity;
    private double              amount;
    private Map<String, String> keyValues;

    public String getId ( ) {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public int getQuantity ( ) {
        return quantity;
    }

    public void setQuantity ( int quantity ) {
        this.quantity = quantity;
    }

    public double getAmount ( ) {
        return amount;
    }

    public void setAmount ( double amount ) {
        this.amount = amount;
    }

    public Map<String, String> getKeyValues ( ) {
        return keyValues;
    }

    public void setKeyValues ( Map<String, String> keyValues ) {
        this.keyValues = keyValues;
    }
}
