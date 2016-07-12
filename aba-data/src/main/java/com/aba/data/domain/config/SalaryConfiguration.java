package com.aba.data.domain.config;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class SalaryConfiguration {
	/**
	 * An item is deemed as vertically integrated when it is planned for consumption by a build
	 * for an item planned for the market.  These should probably have lower point values to help
	 * save isk.  But they can be the same, having them vs not having them increases configuration
	 * decisions a bit but I think the flexibility is well worth it.
	 */
	@JsonProperty
	private Integer hoursPerVerticalIntegrationPoint;
	@JsonProperty
	private Integer hoursPerMarketItemPoint;
	@JsonProperty
	private Integer hoursPerInventionPoint;
	@JsonProperty
	private Integer hoursPerCopyPoint;
	@JsonProperty
	private Integer hoursPerMEPoint;
	@JsonProperty
	private Integer hoursPerTEPoint;
	@JsonProperty
	private Integer hoursPerREPoint;
	@JsonProperty
	private Double pointValue;
	/**
	 * This might potentially be useful... dropping this property in here for the time
	 * being as having this kind of flexibility and depth of configuration may be what's
	 * needed to finally break into the hull market as a corp that pays its members.  Right
	 * now the cost of salary is far too high and always ends up taking us out of the running :(.
	 */
	@JsonProperty
	private Long outputItemId;
}
