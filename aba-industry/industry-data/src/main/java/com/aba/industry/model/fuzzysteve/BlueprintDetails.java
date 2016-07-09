package com.aba.industry.model.fuzzysteve;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BlueprintDetails {
	@JsonProperty
	private Integer maxProductionLimit;
	@JsonProperty("productTypeID")
	private Long productTypeId;
	@JsonProperty("productTypeName")
	private String productName;
	@JsonProperty
	private Integer productQuantity;
	@JsonProperty("times")
	private Map<Integer, Long> timesInSeconds;
	@JsonProperty
	private String[] facilities;
	@JsonProperty
	private Integer techLevel;
	@JsonProperty
	private Double adjustedPrice;
	/**
	 * The precursor is mainly for T2 items.  These take a T1 item and manipulate them
	 * with upgraded hardware to make them more beterer.
	 */
	@JsonProperty
	private Double precursorAdjustedPrice;
	@JsonProperty
	private Long precursorTypeId;
	@JsonProperty("probability")
	private Double baseProbability;
}
