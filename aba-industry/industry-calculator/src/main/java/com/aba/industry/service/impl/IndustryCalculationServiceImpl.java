package com.aba.industry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.data.domain.config.InventionSkillConfiguration;
import com.aba.industry.config.BuildOrBuyConfigurationService;
import com.aba.industry.config.OverheadConfigurationService;
import com.aba.industry.fetch.client.BuildRequirementsProvider;
import com.aba.industry.invention.InventionCalculator;
import com.aba.industry.manufacturing.ManufacturingCalculator;
import com.aba.industry.model.BuildCalculationResult;
import com.aba.industry.overhead.OverheadCalculator;
import com.aba.industry.service.IndustryCalculationService;

@Service
public class IndustryCalculationServiceImpl implements IndustryCalculationService {

	@Autowired
	private BuildOrBuyConfigurationService buildOrBuyService;
	
	@Autowired
	private OverheadConfigurationService overheadService;
	
	@Autowired
	private InventionCalculator inventionCalc;
	
	@Autowired
	private ManufacturingCalculator manufacturingCalc;
	
	@Autowired
	private OverheadCalculator overheadCalculator;
	
	@Autowired
	private BuildRequirementsProvider buildReqProvider;
	
	@Override
	public BuildCalculationResult calculateBuildCosts(Long outputTypeId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public BuildCalculationResult calculateBuildCosts(Long outputTypeId, IndustrySkillConfiguration industrySkills,
			InventionSkillConfiguration inventionSkills, Integer meLevel, boolean findCurrentPrices, boolean useBuildOrBuyConfigurations) {
		// TODO Auto-generated method stub
		return null;
	}
}
