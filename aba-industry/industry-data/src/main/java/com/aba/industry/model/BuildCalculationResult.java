/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.model;

import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.devfleet.crest.model.CrestMarketOrder;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode( callSuper = true )
@ToString( callSuper = true )
public class BuildCalculationResult extends CalculationResult {
    /**
     * The {@link IndustrySkillConfiguration} used to calculate this result.
     */
    @JsonProperty
    private IndustrySkillConfiguration skillConfiguration;

    @JsonProperty
    private Double buildCost = 0d;
    @JsonProperty
    private Map<Integer, CrestMarketOrder> lowestSellOrders;
    @JsonProperty
    private Map<Integer, CrestMarketOrder> highestSellOrders;
    @JsonProperty
    private FreightDetails                 toBuildLocationFreight;
    @JsonProperty
    private FreightDetails                 fromBuildLocationFreight;
    @JsonProperty
    private InventionCalculationResult     inventionResult;
    @JsonProperty
    private List<BuildCalculationResult>   childBuilds;

    protected Double getTotalCostInternal ( ) {
        Double result = 0d;

        result += buildCost;
        result += inventionResult != null ? inventionResult.getTotalCost() : 0d;
        result += toBuildLocationFreight != null ? toBuildLocationFreight.getCharge() : 0d;
        result += fromBuildLocationFreight != null ? fromBuildLocationFreight.getCharge() : 0d;

        return result;
    }
}
