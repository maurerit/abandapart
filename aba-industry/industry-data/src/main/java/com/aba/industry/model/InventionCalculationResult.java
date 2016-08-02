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

import com.aba.data.domain.config.InventionSkillConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode( callSuper = true )
public class InventionCalculationResult extends CalculationResult {
    /**
     * The {@link InventionSkillConfiguration} used to calculate this result.
     */
    @JsonProperty
    private InventionSkillConfiguration skillConfiguration;

    @JsonProperty
    private Double inventionRunCost;
    @JsonProperty
    private Double costPerSuccessfulInventionRun = 0d;
    @JsonProperty
    private Double  blueprintCopyCost;
    @JsonProperty
    private Double  probability;
    @JsonProperty
    private Integer outputTypeId;

    @JsonProperty
    private Integer   resultingME;
    @JsonProperty
    private Integer   resultingTE;
    @JsonProperty
    private Long      resultingRuns;
    @JsonProperty
    private Decryptor decryptor;

    protected Double getTotalCostInternal ( ) {
        Double result = 0d;

        result += costPerSuccessfulInventionRun;

        return result;
    }

    @JsonProperty( "costPerBlueprintRun" )
    public Double getCostPerBlueprintRun ( ) {
        return this.costPerSuccessfulInventionRun / this.resultingRuns;
    }
}
