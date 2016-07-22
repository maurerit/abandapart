package com.aba.industry.model;

import com.aba.data.domain.config.InventionSkillConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class InventionCalculationResult extends CalculationResult {
	/**
	 * The {@link InventionSkillConfiguration} used to calculate this result.
	 */
	@JsonProperty
	private InventionSkillConfiguration skillConfiguration;
	
	@JsonProperty
	private Double inventionRunCost;
	@JsonProperty
	private Double costPerSuccessfulInventionRun = 0d;
	@JsonProperty
	private Double blueprintCopyCost;
	@JsonProperty
	private Double probability;
	@JsonProperty
	private Long outputTypeId;
	
	@JsonProperty
	private Integer resultingME;
	@JsonProperty
	private Integer resultingTE;
	@JsonProperty
	private Integer resultingRuns;
	@JsonProperty
	private Decryptor decryptor;
	
	protected Double getTotalCostInternal ( ) {
		Double result = 0d;
		
		result += costPerSuccessfulInventionRun;
		
		return result;
	}
	
	@JsonProperty("costPerBlueprintRun")
	public Double getCostPerBlueprintRun ( ) {
		return this.costPerSuccessfulInventionRun / this.resultingRuns;
	}
}
