package com.aba.data.domain.config;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class InventionSkillConfiguration {
	@Enumerated(EnumType.STRING)
	@Id
	private ConfigurationType preference;
	private Integer encryptionSkillLevel;
	private Integer datacoreOneSkillLevel;
	private Integer datacoreTwoSkillLevel;
}
