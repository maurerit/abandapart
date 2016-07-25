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

import com.aba.data.domain.config.ConfigurationType;
import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.data.domain.config.InventionSkillConfiguration;
import com.aba.industry.ItemCost;
import com.aba.industry.config.OverheadConfigurationService;
import com.aba.industry.fetch.client.BuildRequirementsProvider;
import com.aba.industry.fetch.client.CostIndexProvider;
import com.aba.industry.invention.InventionCalculator;
import com.aba.industry.manufacturing.ManufacturingCalculator;
import com.aba.industry.model.Activity;
import com.aba.industry.model.ActivityMaterialWithCost;
import com.aba.industry.model.BuildCalculationResult;
import com.aba.industry.model.InventionCalculationResult;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;
import com.aba.industry.overhead.OverheadCalculator;
import com.aba.industry.service.IndustryCalculationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RunWith( MockitoJUnitRunner.class )
public class IndustryCalculationServiceImplTests {
    @Mock
    BuildRequirementsProvider    buildRequirementsProvider;
    @Mock
    CostIndexProvider            costIndexProvider;
    @Mock
    OverheadConfigurationService overheadConfigurationService;
    @Mock
    OverheadCalculator           overheadCalculator;
    @Mock
    InventionCalculator          inventionCalculator;
    @Mock
    ManufacturingCalculator      manufacturingCalculator;
    private ObjectMapper mapper = new ObjectMapper();
    private MapType                     mapType;
    private Map<Integer, ItemCost>      itemCosts;
    private SystemCostIndexes           costIndexes;
    private BlueprintData               bpData;
    private IndustrySkillConfiguration  industrySkills;
    private InventionSkillConfiguration inventionSkills;
    private InventionCalculationResult  inventionCalculationResult;
    private BuildCalculationResult      buildCalculationResult;
    @InjectMocks
    private IndustryCalculationService service = new IndustryCalculationServiceImpl();

    @Before
    public void dataSetupBeforeTests ( ) throws IOException {
        InputStream bpDetailsIS = IndustryCalculationServiceImplTests.class.getResourceAsStream(
                "/testSleipnirWithNullDecryptor-BlueprintDetails.json" );
        InputStream costIndexesIS = IndustryCalculationServiceImplTests.class.getResourceAsStream(
                "/testSleipnirWithNullDecryptor-CostIndexes.json" );
        InputStream itemCostIS = IndustryCalculationServiceImplTests.class.getResourceAsStream(
                "/testSleipnirWithNullDecryptor-ItemCosts.json" );
        InputStream buildCalculationResultIS = IndustryCalculationServiceImplTests.class.getResourceAsStream(
                "/testSleipnirWithNullDecryptor-Output.json" );

        TypeFactory typeFactory = mapper.getTypeFactory();
        mapType = typeFactory.constructMapType( HashMap.class, Integer.class, ItemCost.class );

        itemCosts = mapper.readValue( itemCostIS, mapType );

        costIndexes = mapper.readValue( costIndexesIS, SystemCostIndexes.class );
        bpData = mapper.readValue( bpDetailsIS, BlueprintData.class );
        buildCalculationResult = mapper.readValue( buildCalculationResultIS, BuildCalculationResult.class );

        inventionSkills = new InventionSkillConfiguration();

        inventionSkills.setDatacoreOneSkillLevel( 3 );
        inventionSkills.setDatacoreTwoSkillLevel( 3 );
        inventionSkills.setEncryptionSkillLevel( 4 );

        industrySkills = new IndustrySkillConfiguration();

        industrySkills.setAdvancedIndustrySkillLevel( 5 );
        industrySkills.setIndustrySkillLevel( 5 );
        industrySkills.setPreference( ConfigurationType.EXCEPTIONAL );

        for ( ActivityMaterialWithCost am : bpData.getActivityMaterials()
                                                  .get( Activity.INVENTION.getActivityId() ) ) {
            ItemCost ic = itemCosts.get( am.getTypeId()
                                           .intValue() );

            am.setCost( ic.getSell() );
            am.setAdjustedCost( ic.getAdjusted() );
        }

        for ( ActivityMaterialWithCost am : bpData.getActivityMaterials()
                                                  .get( Activity.MANUFACTURING.getActivityId() ) ) {
            ItemCost ic = itemCosts.get( am.getTypeId()
                                           .intValue() );

            am.setCost( ic.getSell() );
            am.setAdjustedCost( ic.getAdjusted() );
        }

        inventionCalculationResult = new InventionCalculationResult();
        inventionCalculationResult.setSeconds( 2000l );
    }

    @Test
    public void testThatServiceCombinesCalculationResults ( ) throws IOException {
        Mockito.when( buildRequirementsProvider.getBlueprintData( 22444l ) )
               .thenReturn( this.bpData );
        Mockito.when( costIndexProvider.getSystemCostIndexes( "Atreen" ) )
               .thenReturn( this.costIndexes );
        Mockito.when( overheadConfigurationService.getSalaryConfiguration() )
               .thenReturn( null );
        Mockito.when( overheadConfigurationService.getFreightConfiguration() )
               .thenReturn( null );
        Mockito.when( inventionCalculator.calculateInventionCosts( costIndexes, 0.0d, bpData, null,
                                                                   inventionSkills ) )
               .thenReturn( this.inventionCalculationResult );
        Mockito.when( overheadCalculator.getSalary( Activity.INVENTION, 2000l ) )
               .thenReturn( inventionCalculationResult.getSalaryCost()
                                                      .doubleValue() / 60 / 60 / 40 * 200000 );
        Mockito.when( overheadCalculator.getSalary( Activity.MANUFACTURING, buildCalculationResult.getSeconds() ) )
               .thenReturn( buildCalculationResult.getSeconds()
                                                  .doubleValue() / 60 / 60 / 2 * 200000 );
        Mockito.when( manufacturingCalculator.calculateBuildCost( costIndexes, 0.0d, bpData, 2, 4, industrySkills ) )
               .thenReturn( buildCalculationResult );


        BuildCalculationResult result = this.service.calculateBuildCosts( "Atreen", 22444l, industrySkills,
                                                                          inventionSkills, 2, 4, null, false,
                                                                          false );

        Assert.assertEquals( buildCalculationResult.getSeconds()
                                                   .doubleValue() / 60 / 60 / 2 * 200000, result.getSalaryCost(),
                             0.01 );
        Assert.assertEquals( inventionCalculationResult.getSalaryCost()
                                                       .doubleValue() / 60 / 60 / 40 * 200000,
                             result.getInventionResult()
                                   .getSalaryCost(), 0.01 );
        Assert.assertNotNull( buildCalculationResult.getFromBuildLocationFreight() );
        Assert.assertNotNull( buildCalculationResult.getToBuildLocationFreight() );

    }

}
