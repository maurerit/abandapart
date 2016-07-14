package com.aba.industry.manufacturing;

import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.industry.model.BuildCalculationResult;

public interface ManufacturingCalculator {
	/**
	 * Calculates the cost and materials required of the provided output with the provided skills at the provided me level.
	 * 
	 * @param outputTypeId
	 * @param industrySkills
	 * @param meLevel
	 * @param findCurrentPrices
	 * @param useBuildOrBuyConfigurations
	 * @return
	 * @throws BuildCalculationFailureException
	 */
	BuildCalculationResult calculateBuildCost (
			Long outputTypeId,
			IndustrySkillConfiguration industrySkills,
			Integer meLevel,
			boolean findCurrentPrices,
			boolean useBuildOrBuyConfigurations) throws BuildCalculationFailureException;
}
