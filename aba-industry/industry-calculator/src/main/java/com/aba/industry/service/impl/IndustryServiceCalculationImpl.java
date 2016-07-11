package com.aba.industry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.data.domain.config.InventionSkillConfiguration;
import com.aba.industry.config.BuildOrBuyConfigurationService;
import com.aba.industry.model.BuildCalculationResult;
import com.aba.industry.service.IndustryCalculationService;

@Service
public class IndustryServiceCalculationImpl implements IndustryCalculationService {

	@Autowired
	private BuildOrBuyConfigurationService buildOrBuyService;
	
	@Override
	public BuildCalculationResult calculateBuildCosts(Long outputTypeId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public BuildCalculationResult calculateBuildCosts(Long outputTypeId, IndustrySkillConfiguration industrySkills,
			InventionSkillConfiguration inventionSkills, boolean findCurrentPrices, boolean useBuildOrBuyConfigurations) {
		// TODO Auto-generated method stub
		return null;
	}
}
