package com.aba.industry.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Skill {
	@JsonProperty("typeid")
	private long typeId;
	@JsonProperty
	private String name;
	@JsonProperty
	private String level;
}
