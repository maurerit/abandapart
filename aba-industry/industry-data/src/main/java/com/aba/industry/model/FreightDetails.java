package com.aba.industry.model;

import com.aba.data.domain.config.FreightConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FreightDetails {
	@JsonProperty
	private Long startStationId;
	@JsonProperty
	private Long endStationId;
	@JsonProperty
	private String startStationName;
	@JsonProperty
	private String endStationName;
	@JsonProperty
	private Double charge;
	@JsonProperty
	private FreightConfiguration freightConfigUsed;
}
