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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties( allowGetters = true, value = "totalCost" )
public abstract class CalculationResult {
    /**
     * A list of {@link ActivityMaterialWithCost} excluding any {@link Decryptor} used in the process.
     */
    @JsonProperty
    private List<ActivityMaterialWithCost> materialsWithCost;
    @JsonProperty
    private List<Skill>                    requiredSkills;
    @JsonProperty
    private Double                         installationFees;
    @JsonProperty
    private Double                         installationTax;
    @JsonProperty
    private Long                           seconds;
    @JsonProperty
    private Double salaryCost = 0d;

    @JsonProperty
    public Double getTotalCost ( ) {
        Double result = 0d;

        //Installation fees and tax have the potential to be null as not all producers listen to the fact that
        //all costs will be handled by the corp (so they're working at a reduced rate due to this and should
        //be guided that their isk is not needed in this process and the corp will indeed cover these costs).
        result += installationFees != null ? installationFees : 0d;
        result += installationTax != null ? installationTax : 0d;
        result += getTotalCostInternal();

        return result;
    }

    protected abstract Double getTotalCostInternal ( );

    //This is only here really to appease jackson.  It see's this property and attempts to set it but doesn't know
    //that it was a generated property.  Probably bad practice to do this but meh.
    @JsonIgnore
    public void setTotalCost ( ) { }
}
