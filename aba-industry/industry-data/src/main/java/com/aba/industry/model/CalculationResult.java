package com.aba.industry.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public abstract class CalculationResult {
	/**
	 * A list of {@link ActivityMaterialWithCost} excluding any {@link Decryptor} used in the process.
	 */
	@JsonProperty
	private List<ActivityMaterialWithCost> materialsWithCost;
	@JsonProperty
	private List<Skill> requiredSkills;
	@JsonProperty
	private Double installationFees;
	@JsonProperty
	private Double installationTax;
	@JsonProperty
	private Long seconds;
	@JsonProperty
	private Double salaryCost = 0d;
	
	protected abstract Double getTotalCostInternal ( );
	
	@JsonProperty("totalCost")
	public Double getTotalCost ( ) {
		Double result = 0d;
		
		//Installation fees and tax have the potential to be null as not all producers listen to the fact that
		//all costs will be handled by the corp (so they're working at a reduced rate due to this and should
		//be guided that their isk is not needed in this process and the corp will indeed cover these costs).
		result += installationFees != null ? installationFees : 0d;
		result += installationTax != null ? installationTax : 0d;
		result += getTotalCostInternal();
		
		return result;
	}
}
