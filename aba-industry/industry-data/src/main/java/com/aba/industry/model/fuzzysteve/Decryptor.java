package com.aba.industry.model.fuzzysteve;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Decryptor {
	@JsonProperty
	private String name;
	@JsonProperty("typeid")
	private Long typeId;
	@JsonProperty
	private Double multiplier;
	@JsonProperty
	private Integer me;
	@JsonProperty
	private Integer te;
	@JsonProperty
	private Integer runs;
}
