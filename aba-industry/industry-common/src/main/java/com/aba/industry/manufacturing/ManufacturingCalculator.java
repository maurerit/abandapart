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

package com.aba.industry.manufacturing;

import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.industry.model.BuildCalculationResult;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;

public interface ManufacturingCalculator {
    /**
     * Calculates the cost and materials required of the provided output with the provided skills at the provided me
     * level.
     *
     * @param costIndexes
     * @param taxMultiplier
     * @param bpData
     * @param meLevel
     * @param teLevel
     * @param industrySkills
     * @return
     */
    BuildCalculationResult calculateBuildCost (
            SystemCostIndexes costIndexes,
            Double taxMultiplier,
            BlueprintData bpData,
            Integer meLevel,
            Integer teLevel,
            IndustrySkillConfiguration industrySkills );
}
