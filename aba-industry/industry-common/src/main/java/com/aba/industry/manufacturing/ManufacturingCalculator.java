package com.aba.industry.manufacturing;

import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.industry.model.BuildCalculationResult;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;

public interface ManufacturingCalculator {
	/**
	 * Calculates the cost and materials required of the provided output with the provided skills at the provided me level.
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
			IndustrySkillConfiguration industrySkills);
}
