package com.aba.industry.model.fuzzysteve;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SystemCostIndexes {
	@JsonProperty
	private String solarSystemName;
	@JsonProperty("solarSystemID")
	private Long solarSystemId;
	@JsonProperty
	private Map<Integer, Double> costIndexes;
}
