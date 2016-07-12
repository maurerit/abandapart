package com.aba.data.domain.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FreightConfiguration {
	@JsonProperty
	private Double iskPerJump;
	@JsonProperty
	private Double metersCubedMultiplier;
	@JsonProperty
	private Double collateralMultiplier;
}
