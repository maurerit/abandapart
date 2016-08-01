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

package com.aba.industry.service;

import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.data.domain.config.InventionSkillConfiguration;
import com.aba.industry.model.BuildCalculationResult;
import com.aba.industry.model.Decryptor;

/**
 * Provides methods to calcuate full build calculations including any overheads if there are any to be considered.
 *
 * @author maurerit
 */
public interface IndustryCalculationService {
    /**
     * Convenience overload of
     * {@link IndustryCalculationService#calculateBuildCosts(String, Integer, IndustrySkillConfiguration, InventionSkillConfiguration, Integer, Integer, Decryptor, boolean, boolean)}
     * which assumes all 5's for skills, true for findCurrentPrices and false for useBuildOrBuyConfigurations.
     *
     * @param systemName
     * @param outputTypeId
     * @return
     */
    BuildCalculationResult calculateBuildCosts ( String systemName, Integer outputTypeId );

    /**
     * Provides a mechanism to easily calculate the output of a full build with configuration options to either use
     * current stock prices or download them from the web.  Can also dive as deep into the supply chain as is specified
     * by a dependent service provided by a {@link com.aba.industry.config.BuildOrBuyConfigurationService}
     *
     * @param systemName                  The name of the system to use for the cost indexes
     * @param outputTypeId                The final output type for which this build calculation is being performed
     * @param industrySkills              The industry skill configuration to use for determining times and therefore
     *                                    salaries
     * @param inventionSkills             The invention skills configuration to use for determining probability and
     *                                    total invention costs
     * @param findCurrentPrices           Whether to reach out to the internet or used store material prices
     * @param meLevel                     The Material Efficiency level of the bluepring being used
     * @param teLevel                     The Time Efficiency level of the blueprint being used
     * @param useBuildOrBuyConfigurations Whether to traverse the entire build tree or ask a configuration service to
     *                                    find
     *                                    what is and is not being build
     * @return A final 'report' representing all relevant data for this calculated build.
     */
    BuildCalculationResult calculateBuildCosts (
            String systemName,
            Integer outputTypeId,
            IndustrySkillConfiguration industrySkills,
            InventionSkillConfiguration inventionSkills,
            Integer meLevel,
            Integer teLevel,
            Decryptor decryptor,
            boolean findCurrentPrices,
            boolean useBuildOrBuyConfigurations );
}
