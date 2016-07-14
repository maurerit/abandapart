package com.aba.industry.manufacturing.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.industry.fetch.client.BuildRequirementsProvider;
import com.aba.industry.manufacturing.BuildCalculationFailureException;
import com.aba.industry.manufacturing.ManufacturingCalculator;
import com.aba.industry.model.BuildCalculationResult;
import com.aba.industry.model.fuzzysteve.BlueprintData;

/**
 * @author maurerit
 */
@Component
public class ManufacturingCalculatorImpl implements ManufacturingCalculator {
	private static final Logger logger = LoggerFactory.getLogger(ManufacturingCalculator.class);
    @Autowired
    private BuildRequirementsProvider buildReqProvider;
    
    //TODO: Spring has a retry annotation I believe, look into that.
    @Override
    public BuildCalculationResult calculateBuildCost (
    		Long outputTypeId,
			IndustrySkillConfiguration industrySkills,
			Integer meLevel,
			boolean findCurrentPrices,
			boolean useBuildOrBuyConfigurations) throws BuildCalculationFailureException
    {
    	BlueprintData bpData;
		try {
			bpData = buildReqProvider.getBlueprintData(outputTypeId);
		} catch (IOException e) {
			logger.error("Retrieving build requirements from remote server",e);
			throw new BuildCalculationFailureException(e);
		}
		
    	if ( bpData.getActivityMaterials() == null || !bpData.getActivityMaterials().containsKey(1) ) {
    		throw new BuildCalculationFailureException("Does not contain anything useful");
    	}
    	
    	BuildCalculationResult result = new BuildCalculationResult();
    	return result;
    }
}
