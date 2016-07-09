package com.aba.industry.model.fuzzysteve;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ActivityMaterial {
	@JsonProperty("typeid")
	private Long typeId;
	@JsonProperty
	private String name;
	@JsonProperty
	private Long quantity;
	@JsonProperty("maketype")
	private Long blueprintTypeId;
}
