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

package com.aba.industry.invention;

import com.aba.data.domain.config.InventionSkillConfiguration;
import com.aba.industry.model.Decryptor;
import com.aba.industry.model.InventionCalculationResult;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;

/**
 * Represents the algorithm used by CCP to calculate invention costs.  Implementations CAN do more
 * but they're spec'ed out with only the ability to do the calculation.  Something else should gather
 * the costs for the required inputs and other stuff.
 *
 * @author maurerit
 */
public interface InventionCalculator {
    InventionCalculationResult calculateInventionCosts (
            SystemCostIndexes costIndexes,
            Double taxRate,
            BlueprintData bpData,
            Decryptor decryptor,
            InventionSkillConfiguration skillConfiguration );
}
