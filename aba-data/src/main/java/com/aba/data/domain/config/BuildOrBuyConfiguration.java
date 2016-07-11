package com.aba.data.domain.config;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class BuildOrBuyConfiguration {
	public static enum BuildOrBuy {
		BUILD,
		BUY
	}
	private Long typeId;
	private BuildOrBuy buildOrBuy;
}
