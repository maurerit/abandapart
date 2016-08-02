/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.model.fuzzysteve;

import com.aba.industry.model.IndustryActivities;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class BlueprintDetails {
    @JsonProperty
    private Long               maxProductionLimit;
    @JsonProperty( "productTypeID" )
    private Integer            productTypeId;
    @JsonProperty( "productTypeName" )
    private String             productName;
    @JsonProperty
    private Integer            productQuantity;
    /**
     * A map of {@link IndustryActivities#activityId} to times in seconds unmodified.
     */
    @JsonProperty( "times" )
    private Map<Integer, Long> timesInSeconds;
    @JsonProperty
    private String[]           facilities;
    @JsonProperty
    private Integer            techLevel;
    @JsonProperty
    private Double             adjustedPrice;
    /**
     * The precursor is mainly for T2 items.  These take a T1 item and manipulate them
     * with upgraded hardware to make them more beterer.
     */
    @JsonProperty
    private Double             precursorAdjustedPrice;
    @JsonProperty
    private Integer            precursorTypeId;
    @JsonProperty( "probability" )
    private Double             baseProbability;
}
