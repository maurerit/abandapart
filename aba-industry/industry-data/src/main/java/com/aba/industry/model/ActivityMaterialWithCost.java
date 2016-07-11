package com.aba.industry.model;

import com.aba.industry.model.fuzzysteve.ActivityMaterial;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ActivityMaterialWithCost extends ActivityMaterial {
	private CostSource source;
	private Double cost;
}
