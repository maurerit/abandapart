package com.aba.industry.model.fuzzysteve;

import java.util.List;
import java.util.Map;

import com.aba.industry.model.Decryptor;
import com.aba.industry.model.Skill;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BlueprintData {
	@JsonProperty("requestedid")
	long requestedId;
	@JsonProperty
	Map<Integer, List<Skill>> blueprintSkills;
	@JsonProperty
	BlueprintDetails blueprintDetails;
	@JsonProperty
	Map<Integer, List<ActivityMaterial>> activityMaterials;
	@JsonProperty
	List<Decryptor> decryptors;
}
