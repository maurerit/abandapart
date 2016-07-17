package com.aba.industry.invention.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.aba.data.domain.config.InventionSkillConfiguration;
import com.aba.industry.invention.InventionCalculator;
import com.aba.industry.model.ActivityMaterialWithCost;
import com.aba.industry.model.Decryptor;
import com.aba.industry.model.InventionCalculationResult;
import com.aba.industry.model.fuzzysteve.BlueprintDetails;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;

/**
 * @author maurerit
 */
@Component
public class InventionCalculatorImpl implements InventionCalculator {
	@Override
	public InventionCalculationResult calculateInventionCosts(
			SystemCostIndexes costIndexes,
			BlueprintDetails bpDetails,
			Decryptor decryptor,
			List<ActivityMaterialWithCost> amWithCost,
			InventionSkillConfiguration skillConfiguration)
	{
		InventionCalculationResult result = new InventionCalculationResult();
		
		result.setOutputTypeId(bpDetails.getProductTypeId());
		

		Double decryptorMultiplier = decryptor != null ? decryptor.getMultiplier() : 1; 
		
		result.setProbability(this.getInventionChance(bpDetails, skillConfiguration, decryptorMultiplier));
		
		return result;
	}
	
	private double getInventionChance ( BlueprintDetails bpDetails, InventionSkillConfiguration skillConfiguration, Double decryptorMultiplier ) {
		return Math.min(
				((bpDetails.getBaseProbability() * 100) *
				(1 +
					(
						(
							skillConfiguration.getDatacoreOneSkillLevel() +
							skillConfiguration.getDatacoreTwoSkillLevel()
						) /
						30
					) *
					(
						skillConfiguration.getEncryptionSkillLevel() /
						40
					)
				)) *
				decryptorMultiplier,
				100);
	}
}
