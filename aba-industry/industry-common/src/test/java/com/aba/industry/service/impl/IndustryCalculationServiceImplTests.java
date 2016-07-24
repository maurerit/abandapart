/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.service.impl;

import com.aba.industry.config.BuildOrBuyConfigurationService;
import com.aba.industry.config.OverheadConfigurationService;
import com.aba.industry.fetch.client.BuildRequirementsProvider;
import com.aba.industry.invention.InventionCalculator;
import com.aba.industry.manufacturing.ManufacturingCalculator;
import com.aba.industry.overhead.OverheadCalculator;
import com.aba.industry.service.IndustryCalculationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith( MockitoJUnitRunner.class )
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
    public void testCalculateBuildCostsLong ( ) {
//		fail("Not yet implemented");
    }

    @Test
    public void testCalculateBuildCostsLongIndustrySkillConfigurationInventionSkillConfigurationIntegerBooleanBoolean ( ) {
//		fail("Not yet implemented");
    }

}
