package com.aba.industry.manufacturing.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.industry.manufacturing.ManufacturingCalculator;
import com.aba.industry.model.Activities;
import com.aba.industry.model.ActivityMaterialWithCost;
import com.aba.industry.model.BuildCalculationResult;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;

/**
 * @author maurerit
 */
@Component
public class ManufacturingCalculatorImpl implements ManufacturingCalculator {
	private static final Logger logger = LoggerFactory.getLogger(ManufacturingCalculator.class);
    
    @Override
    public BuildCalculationResult calculateBuildCost (
    		SystemCostIndexes costIndexes,
			Double taxMultiplier,
			BlueprintData bpData,
			Integer meLevel,
			Integer teLevel,
			IndustrySkillConfiguration industrySkills)
    {
    	BuildCalculationResult result = new BuildCalculationResult();
    	result.setSkillConfiguration(industrySkills);
    	
    	/*
    	 * for (materialid in blueprintData['activityMaterials'][1]) {
         *   material=blueprintData['activityMaterials'][1][materialid];
         *   reducedquantity=material.quantity*(1-(me/100))*facilityme[facility]*(1-(teamMe/100));
    	 */
    	
    	List<ActivityMaterialWithCost> materialsReduced = new ArrayList<>();
    	Double buildCost = 0d;
    	Double runCost = 0d;
    	for ( ActivityMaterialWithCost material : bpData.getActivityMaterials().get(Activities.MANUFACTURING.getActivityId()) ) {
    		ActivityMaterialWithCost materialReduced = new ActivityMaterialWithCost();
    		
    		materialReduced.setBlueprintTypeId(material.getBlueprintTypeId());
    		materialReduced.setCost(material.getCost());
    		materialReduced.setAdjustedCost(material.getAdjustedCost());
    		materialReduced.setName(material.getName());
    		materialReduced.setQuantity(Math.round(material.getQuantity() * (1 - ( meLevel.doubleValue() / 100 ))/* * facility.getFacilityMe()*/));
    		materialReduced.setSource(material.getSource());
    		materialReduced.setTypeId(material.getTypeId());
    		
    		materialsReduced.add(materialReduced);
    		
    		buildCost += materialReduced.getCost() * materialReduced.getQuantity();
    		runCost += materialReduced.getAdjustedCost() == null ? 0d : materialReduced.getAdjustedCost() * material.getQuantity();
    	}
    	result.setBuildCost(buildCost);
    	result.setMaterialsWithCost(materialsReduced);
    	runCost = (runCost * costIndexes.getCostIndexes().get(Activities.MANUFACTURING.getActivityId())) * taxMultiplier;
    	
    	//Once the loop is done
    	//$('#installCost').number((runCost*indexData.costIndexes["1"])*taxmultiplier*(1+(salary/100)),2);
    	
    	result.setInstallationFees(runCost);
    	result.setInstallationTax(runCost * .1d);
    	
    	return result;
    }
}
