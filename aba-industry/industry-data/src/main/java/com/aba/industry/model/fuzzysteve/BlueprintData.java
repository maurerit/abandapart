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

import com.aba.industry.model.ActivityMaterialWithCost;
import com.aba.industry.model.Decryptor;
import com.aba.industry.model.IndustryActivities;
import com.aba.industry.model.Skill;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BlueprintData {
    @JsonProperty( "requestedid" )
    long                                         requestedId;
    /**
     * A map of {@link IndustryActivities#activityId} to a list of the {@link Skill} requirements.
     */
    @JsonProperty
    Map<Integer, List<Skill>>                    blueprintSkills;
    @JsonProperty
    BlueprintDetails                             blueprintDetails;
    /**
     * A map of {@link IndustryActivities#activityId} to {@link ActivityMaterial}.
     */
    @JsonProperty
    Map<Integer, List<ActivityMaterialWithCost>> activityMaterials;
    @JsonProperty
    List<Decryptor>                              decryptors;
}
