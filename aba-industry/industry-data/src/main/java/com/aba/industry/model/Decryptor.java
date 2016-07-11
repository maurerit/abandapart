package com.aba.industry.model;

import com.aba.industry.model.fuzzysteve.ActivityMaterial;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Decryptor extends ActivityMaterial {
	@JsonProperty
	private Double multiplier;
	@JsonProperty
	private Integer me;
	@JsonProperty
	private Integer te;
	@JsonProperty
	private Integer runs;
}
