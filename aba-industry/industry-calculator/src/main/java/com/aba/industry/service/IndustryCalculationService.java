package com.aba.industry.service;

import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.data.domain.config.InventionSkillConfiguration;
import com.aba.industry.model.BuildCalculationResult;

/**
 * Provides methods to calcuate full build calculations including any overheads if there are any to be considered.
 * 
 * @author maurerit
 */
public interface IndustryCalculationService {
	/**
	 * Convenience overload of {@link IndustryCalculationService#calculateBuildCosts(Long, IndustrySkillConfiguration, InventionSkillConfiguration, boolean, boolean)}
	 * which assumes all 5's for skills, true for findCurrentPrices and false for useBuildOrBuyConfigurations.
	 * 
	 * @param outputTypeId
	 * @return
	 */
	BuildCalculationResult calculateBuildCosts ( Long outputTypeId );
	
	/**
	 * Provides a mechanism to easily calculate the output of a full build with configuration options to either use
	 * current stock prices or download them from the web.  Can also dive as deep into the supply chain as is specified
	 * by a dependent service provided by a {@link com.aba.industry.config.BuildOrBuyConfigurationService}
	 * 
	 * @param outputTypeId The final output type for which this build calculation is being performed
	 * @param industrySkills The industry skill configuration to use for determining times and therefore salaries
	 * @param inventionSkills The invention skills configuration to use for determining probability and total invention costs
	 * @param findCurrentPrices Whether to reach out to the internet or used store material prices
	 * @param meLevel The Material Efficiency level of the blue being used
	 * @param useBuildOrBuyConfigurations Whether to traverse the entire build tree or ask a configuration service to find
	 * what is and is not being build
	 * @return A final 'report' representing all relevant data for this calculated build.
	 */
	BuildCalculationResult calculateBuildCosts ( Long outputTypeId,
			IndustrySkillConfiguration industrySkills,
			InventionSkillConfiguration inventionSkills,
			Integer meLevel,
			boolean findCurrentPrices,
			boolean useBuildOrBuyConfigurations);
}
