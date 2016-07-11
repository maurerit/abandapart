package com.aba.data.domain.config;

import lombok.Getter;

@Getter
public enum ConfigurationType {
	REQUIRED(1, "Required"),
	PREFERED(2, "Prefered"),
	EXCEPTIONAL(3, "Exceptional")
	;
	
	private final int rank;
	private final String name;
	
	ConfigurationType ( int rank, String name ) {
		this.rank = rank;
		this.name = name;
	}
}
