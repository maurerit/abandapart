package com.aba.industry.model;

import com.aba.industry.model.fuzzysteve.ActivityMaterial;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
public class ActivityMaterialWithCost extends ActivityMaterial {
	private CostSource source;
	private Double cost;
}
