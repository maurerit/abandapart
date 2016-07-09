package com.aba.industry.model.fuzzysteve;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BlueprintSkills {
	@JsonProperty
	Map<Integer, List<Skill>> skills;
}
