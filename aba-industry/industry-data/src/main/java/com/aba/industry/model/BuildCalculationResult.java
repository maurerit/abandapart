package com.aba.industry.model;

import java.util.List;

import com.aba.data.domain.config.IndustrySkillConfiguration;
import lombok.Data;

@Data
public class BuildCalculationResult {
	/**
	 * The {@link IndustrySkillConfiguration} used to calculate this result.
	 */
	private IndustrySkillConfiguration skillConfiguration;
	private List<ActivityMaterialWithCost> materialsWithCost;
	private Double costPer;
	private InventionCalculationResult inventionResult;
	private List<Skill> requiredSkills;
}
