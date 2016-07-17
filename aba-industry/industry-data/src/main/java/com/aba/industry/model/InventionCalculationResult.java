package com.aba.industry.model;

import java.util.List;

import com.aba.data.domain.config.InventionSkillConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InventionCalculationResult {
	@JsonProperty
	private InventionSkillConfiguration skillConfiguration;
	@JsonProperty
	private Double costPerInventionRun = 0d;
	@JsonProperty
	private Double installationFees;
	@JsonProperty
	private Double installationTax;
	@JsonProperty
	private Double probability;
	@JsonProperty
	private Integer seconds;
	@JsonProperty
	private Long outputTypeId;
	
	@JsonProperty
	private Integer resultingME;
	@JsonProperty
	private Integer resultingTE;
	@JsonProperty
	private Integer resultingRuns;
	
	/**
	 * A list of {@link ActivityMaterialWithCost} excluding any {@link Decryptor} used in the process.
	 */
	@JsonProperty
	private List<ActivityMaterialWithCost> materialsWithCost;
	@JsonProperty
	private Decryptor decryptor;
	@JsonProperty
	private List<Skill> requiredSkills;
	
	@JsonProperty("totalCost")
	public Double getTotalCost ( ) {
		Double result = 0d;
		
		result += costPerInventionRun;
		result += installationFees != null ? installationFees : 0d;
		result += installationTax != null ? installationTax : 0d;
		
		return result;
	}
	
	@JsonProperty("costPerBlueprintRun")
	public Double getCostPerBlueprintRun ( ) {
		return this.costPerInventionRun / this.resultingRuns;
	}
}
