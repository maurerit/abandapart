/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.data.domain.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "build_or_buy_config" )
@Getter
@ToString
@EqualsAndHashCode
public class BuildOrBuyConfiguration {
    @JsonProperty
    private Integer    typeId;
    @JsonProperty
    private BuildOrBuy buildOrBuy;

    public BuildOrBuyConfiguration ( ) {
    }

    public BuildOrBuyConfiguration ( Integer typeId,
                                     BuildOrBuy buildOrBuy )
    {
        this.typeId = typeId;
        this.buildOrBuy = buildOrBuy;
    }

    public enum BuildOrBuy {
        BUILD,
        BUY
    }
}
