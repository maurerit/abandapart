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
package com.aba.eveonline.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Type {
    private I18NString description;
    private Integer graphicID;
    private Integer groupID;
    private Integer iconID;
    private Double mass;
    private I18NString name;
    private Integer portionSize;
    private Boolean published;
    private Double radius;
    private Integer soundID;
    private Double volume;
    private Double capacity;
    private Integer raceID;
    private Double basePrice;
    private Integer marketGroupID;
    private String sofFactionName;
    private Integer factionID;
    private Map<Integer, List<Integer>> masteries;
    private Traits traits;
    private Integer sofMaterialSetID;
}
