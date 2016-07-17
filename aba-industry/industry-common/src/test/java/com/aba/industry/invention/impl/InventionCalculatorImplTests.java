package com.aba.industry.invention.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.aba.data.domain.config.InventionSkillConfiguration;
import com.aba.industry.ItemCost;
import com.aba.industry.model.Activities;
import com.aba.industry.model.ActivityMaterialWithCost;
import com.aba.industry.model.Decryptor;
import com.aba.industry.model.InventionCalculationResult;
import com.aba.industry.model.fuzzysteve.ActivityMaterial;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.BlueprintDetails;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class InventionCalculatorImplTests {

	private InventionCalculatorImpl calcImpl = new InventionCalculatorImpl();
	
	@Test
	public void testSleipnirWithNullDecryptor() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		InputStream bpDetailsIS = InventionCalculatorImplTests.class.getResourceAsStream("/testSleipnirWithNullDecryptor-BlueprintDetails.json");
		InputStream costIndexesIS = InventionCalculatorImplTests.class.getResourceAsStream("/testSleipnirWithNullDecryptor-CostIndexes.json");
		InputStream itemCostIS = InventionCalculatorImplTests.class.getResourceAsStream("/testSleipnirWithNullDecryptor-ItemCosts.json");
		
		TypeFactory typeFactory = mapper.getTypeFactory();
		MapType mapType = typeFactory.constructMapType(HashMap.class, Integer.class, ItemCost.class);
		
		Map<Integer, ItemCost> itemCosts = mapper.readValue(itemCostIS, mapType);
		
		SystemCostIndexes costIndexes = mapper.readValue(costIndexesIS, SystemCostIndexes.class);
		BlueprintData bpData = mapper.readValue(bpDetailsIS, BlueprintData.class);
		BlueprintDetails bpDetails = bpData.getBlueprintDetails();
		Decryptor decryptor = null;
		List<ActivityMaterialWithCost> amWithCost = new ArrayList<>();
		InventionSkillConfiguration skillConfiguration = new InventionSkillConfiguration();
		
		skillConfiguration.setDatacoreOneSkillLevel(3);
		skillConfiguration.setDatacoreTwoSkillLevel(3);
		skillConfiguration.setEncryptionSkillLevel(4);
		
		for ( ActivityMaterial am : bpData.getActivityMaterials().get(Activities.INVENTION.getActivityId()) ) {
			ActivityMaterialWithCost newAmWithCost = new ActivityMaterialWithCost();
			
			ItemCost ic = itemCosts.get(am.getTypeId().intValue());
			
			newAmWithCost.setCost(ic.getSell());
			newAmWithCost.setTypeId(am.getTypeId());
			
			amWithCost.add(newAmWithCost);
		}
		
		InventionCalculationResult result = calcImpl.calculateInventionCosts(costIndexes, bpDetails, decryptor, amWithCost, skillConfiguration);
		
		Assert.assertNotNull("Result should not be null", result);
		Assert.assertEquals(7598848.59, result.getTotalCost(), 0.01);
	}

}
