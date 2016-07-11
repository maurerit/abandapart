package com.aba.industry.model;

import java.util.List;

import lombok.Data;

@Data
public class BuildCalculationResult {
	private Double costPer;
	private InventionCalculationResult inventionResult;
	private List<Skill> requiredSkills;
}
