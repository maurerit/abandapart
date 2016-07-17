package com.aba.industry;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemCost {
	/*
	 * "sell": 141.87,
     * "buy": 166.81, 
     * "adjusted": 0,
     * "average": 309.19
	 */
	@JsonProperty
	private Double sell;
	@JsonProperty
	private Double buy;
	@JsonProperty
	private Double adjusted;
	@JsonProperty
	private Double average;
}
