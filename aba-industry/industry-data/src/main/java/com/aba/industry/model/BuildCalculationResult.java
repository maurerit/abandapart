package com.aba.industry.model;

import java.util.List;

import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class BuildCalculationResult extends CalculationResult {
	/**
	 * The {@link IndustrySkillConfiguration} used to calculate this result.
	 */
	@JsonProperty
	private IndustrySkillConfiguration skillConfiguration;
	
	@JsonProperty
	private Double buildCost = 0d;
	@JsonProperty
	private FreightDetails toBuildLocationFreight;
	@JsonProperty
	private FreightDetails fromBuildLocationFreight;
	@JsonProperty
	private InventionCalculationResult inventionResult;
	@JsonProperty
	private List<BuildCalculationResult> childBuilds;
	
	protected Double getTotalCostInternal ( ) {
		Double result = 0d;
		
		result += buildCost;
		result += inventionResult != null ? inventionResult.getTotalCost() : 0d;
		result += toBuildLocationFreight != null ? toBuildLocationFreight.getCharge() : 0d;
		result += fromBuildLocationFreight != null ? fromBuildLocationFreight.getCharge() : 0d;
		
		return result;
	}
}
