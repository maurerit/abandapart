package com.aba.industry.model;

import java.util.List;

import lombok.Data;

@Data
public class InventionCalculationResult {
	private Double costPerRun;
	private Double probability;
	private Integer seconds;
	private Long outputTypeId;
	private Decryptor decryptorUsed;
	private List<Skill> requiredSkills;
}
