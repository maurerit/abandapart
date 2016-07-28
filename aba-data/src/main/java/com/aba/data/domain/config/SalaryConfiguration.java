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

package com.aba.data.domain.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class SalaryConfiguration {
    /**
     * An item is deemed as vertically integrated when it is planned for consumption by a build
     * for an item planned for the market.  These should probably have lower point values to help
     * save isk.  But they can be the same, having them vs not having them increases configuration
     * decisions a bit but I think the flexibility is well worth it.
     */
    @JsonProperty
    private Integer hoursPerVerticalIntegrationPoint;
    @JsonProperty
    private Integer hoursPerMarketItemPoint;
    @JsonProperty
    private Integer hoursPerInventionPoint;
    @JsonProperty
    private Integer hoursPerCopyPoint;
    @JsonProperty
    private Integer hoursPerMEPoint;
    @JsonProperty
    private Integer hoursPerTEPoint;
    @JsonProperty
    private Integer hoursPerREPoint;
    @JsonProperty
    private Double  pointValue;
    /**
     * This might potentially be useful... dropping this property in here for the time
     * being as having this kind of flexibility and depth of configuration may be what's
     * needed to finally break into the hull market as a corp that pays its members.  Right
     * now the cost of salary is far too high and always ends up taking us out of the running :(.
     */
    @JsonProperty
    private Long    outputItemId;
}
