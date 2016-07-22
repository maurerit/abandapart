package com.aba.industry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aba.data.domain.config.ConfigurationType;
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
	private BuildRequirementsProvider buildReqProvider;
	
	@Autowired
	private InventionCalculator inventionCalc;
	
	@Autowired
	private ManufacturingCalculator manufacturingCalc;
	
	@Autowired
	private OverheadConfigurationService overheadService;
	
	@Autowired
	private OverheadCalculator overheadCalculator;
	
	@Override
	public BuildCalculationResult calculateBuildCosts(Long outputTypeId) {
		IndustrySkillConfiguration industrySkills = new IndustrySkillConfiguration();
		industrySkills.setAdvancedIndustrySkillLevel(5);
		industrySkills.setAdvancedIndustrySkillLevelMultiplier(0.0);
		industrySkills.setIndustrySkillLevel(5);
		industrySkills.setIndustrySkillLevelMultiplier(0.0);
		industrySkills.setPreference(ConfigurationType.PREFERED);
		
		InventionSkillConfiguration inventionSkills = new InventionSkillConfiguration();
		inventionSkills.setDatacoreOneSkillLevel(3);
		inventionSkills.setDatacoreOneSkillLevelMultiplier(0.0);
		inventionSkills.setDatacoreTwoSkillLevel(3);
		inventionSkills.setDatacoreTwoSkillLevelMultiplier(0.0);
		inventionSkills.setEncryptionSkillLevel(4);
		inventionSkills.setEncryptionSkillLevelMultiplier(0.0);
		inventionSkills.setPreference(ConfigurationType.PREFERED);
		return this.calculateBuildCosts(outputTypeId, industrySkills, inventionSkills, 10, true, false);
	}
	
	@Override
	public BuildCalculationResult calculateBuildCosts(
			Long outputTypeId,
			IndustrySkillConfiguration industrySkills,
			InventionSkillConfiguration inventionSkills,
			Integer meLevel,
			boolean findCurrentPrices,
			boolean useBuildOrBuyConfigurations)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
