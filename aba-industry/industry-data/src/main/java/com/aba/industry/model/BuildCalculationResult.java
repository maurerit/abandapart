package com.aba.industry.model;

import java.util.List;

import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BuildCalculationResult {
	/**
	 * The {@link IndustrySkillConfiguration} used to calculate this result.
	 */
	@JsonProperty
	private IndustrySkillConfiguration skillConfiguration;
	@JsonProperty
	private List<ActivityMaterialWithCost> materialsWithCost;
	@JsonProperty
	private Double buildCost = 0d;
	@JsonProperty
	private Double salaryCost = 0d;
	@JsonProperty
	private Double installationFees;
	@JsonProperty
	private Double installationTax;
	@JsonProperty
	private InventionCalculationResult inventionResult;
	@JsonProperty
	private List<Skill> requiredSkills;
	@JsonProperty
	private Long seconds;
	@JsonProperty
	private FreightDetails toBuildLocationFreight;
	@JsonProperty
	private FreightDetails fromBuildLocationFreight;
	@JsonProperty
	private List<BuildCalculationResult> childBuilds;
	
	@JsonProperty
	public Double getTotalCost ( ) {
		Double result = 0d;
		
		result += buildCost;
		result += salaryCost;
		//Installation fees and tax have the potential to be null as not all producers listen to the fact that
		//all costs will be handled by the corp (so they're working at a reduced rate due to this and should
		//be guided that their isk is not needed in this process and the corp will indeed cover these costs).
		result += installationFees != null ? installationFees : 0d;
		result += installationTax != null ? installationTax : 0d;
		result += inventionResult != null ? inventionResult.getTotalCost() : 0d;
		result += toBuildLocationFreight != null ? toBuildLocationFreight.getCharge() : 0d;
		result += fromBuildLocationFreight != null ? fromBuildLocationFreight.getCharge() : 0d;
		
		return result;
	}
}
