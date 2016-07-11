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
	 * @param outputTypeId
	 * @param industrySkills
	 * @param inventionSkills
	 * @param findCurrentPrices
	 * @return
	 */
	BuildCalculationResult calculateBuildCosts ( Long outputTypeId,
			IndustrySkillConfiguration industrySkills,
			InventionSkillConfiguration inventionSkills,
			boolean findCurrentPrices,
			boolean useBuildOrBuyConfigurations);
}
