package com.aba.data.domain.config;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class IndustrySkillConfiguration {
	@Enumerated(EnumType.STRING)
	@Id
	private ConfigurationType preference;
	@NonNull
	private Integer industrySkillLevel;
	@NonNull
	private Integer advancedIndustrySkillLevel;
	/**
	 * Multiplier to be used in wage calculation for each level of the {@link IndustrySkillConfiguration#industrySkillLevel}.
	 */
	private Double industrySkillLevelMultiplier;
	/**
	 * Multiplier to be used in wage calculation for each level of the {@link IndustrySkillConfiguration#advancedIndustrySkillLevel}.
	 */
	private Double advancedIndustrySkillLevelMultiplier;
}
