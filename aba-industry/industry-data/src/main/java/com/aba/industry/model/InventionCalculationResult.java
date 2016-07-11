package com.aba.industry.model;

import java.util.List;

import com.aba.data.domain.config.InventionSkillConfiguration;
import lombok.Data;

@Data
public class InventionCalculationResult {
	private InventionSkillConfiguration skillConfiguration;
	private Double costPerRun;
	private Double probability;
	private Integer seconds;
	private Long outputTypeId;
	/**
	 * A list of {@link ActivityMaterialWithCost} excluding any {@link Decryptor} used in the process.
	 */
	private List<ActivityMaterialWithCost> materialsWithCost;
	private Decryptor decryptorUsed;
	private List<Skill> requiredSkills;
}
