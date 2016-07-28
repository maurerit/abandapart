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

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class IndustrySkillConfiguration {
    @Enumerated( EnumType.STRING )
    @Id
    private ConfigurationType preference;
    @NonNull
    private Integer           industrySkillLevel;
    @NonNull
    private Integer           advancedIndustrySkillLevel;
    /**
     * Multiplier to be used in wage calculation for each level of the
     * {@link IndustrySkillConfiguration#industrySkillLevel}.
     */
    private Double            industrySkillLevelMultiplier;
    /**
     * Multiplier to be used in wage calculation for each level of the
     * {@link IndustrySkillConfiguration#advancedIndustrySkillLevel}.
     */
    private Double            advancedIndustrySkillLevelMultiplier;
}
