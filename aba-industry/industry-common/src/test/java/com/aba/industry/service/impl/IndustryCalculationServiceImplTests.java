package com.aba.industry.service.impl;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.aba.industry.config.BuildOrBuyConfigurationService;
import com.aba.industry.config.OverheadConfigurationService;
import com.aba.industry.fetch.client.BuildRequirementsProvider;
import com.aba.industry.invention.InventionCalculator;
import com.aba.industry.manufacturing.ManufacturingCalculator;
import com.aba.industry.overhead.OverheadCalculator;
import com.aba.industry.service.IndustryCalculationService;

@RunWith(MockitoJUnitRunner.class)
public class IndustryCalculationServiceImplTests {

	@InjectMocks
	private IndustryCalculationService service = new IndustryCalculationServiceImpl();
	
	@Mock
	private BuildOrBuyConfigurationService buildOrBuyService;
	
	@Mock
	private BuildRequirementsProvider buildReqProvider;
	
	@Mock
	private OverheadConfigurationService overheadService;
	
	@Mock
	private InventionCalculator inventionCalc;
	
	@Mock
	private ManufacturingCalculator manufacturingCalc;
	
	@Mock
	private OverheadCalculator overheadCalculator;
	
	@Test
	public void testCalculateBuildCostsLong() {
//		fail("Not yet implemented");
	}

	@Test
	public void testCalculateBuildCostsLongIndustrySkillConfigurationInventionSkillConfigurationIntegerBooleanBoolean() {
//		fail("Not yet implemented");
	}

}
