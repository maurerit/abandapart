package com.aba.industry.model.fuzzysteve;

import java.util.List;
import java.util.Map;

import com.aba.industry.model.ActivityMaterialWithCost;
import com.aba.industry.model.Decryptor;
import com.aba.industry.model.Skill;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BlueprintData {
	@JsonProperty("requestedid")
	long requestedId;
	/**
	 * A map of {@link com.aba.industry.model.Activities#activityId} to a list of the {@link Skill} requirements.
	 */
	@JsonProperty
	Map<Integer, List<Skill>> blueprintSkills;
	@JsonProperty
	BlueprintDetails blueprintDetails;
	/**
	 * A map of {@link com.aba.industry.model.Activities#activityId} to {@link ActivityMaterial}.
	 */
	@JsonProperty
	Map<Integer, List<ActivityMaterialWithCost>> activityMaterials;
	@JsonProperty
	List<Decryptor> decryptors;
}
