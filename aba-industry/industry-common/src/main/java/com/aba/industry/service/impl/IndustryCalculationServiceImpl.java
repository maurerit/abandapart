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

import com.aba.ApplicationException;
import com.aba.data.domain.config.*;
import com.aba.industry.config.BuildOrBuyConfigurationService;
import com.aba.industry.config.OverheadConfigurationService;
import com.aba.industry.fetch.client.BuildRequirementsProvider;
import com.aba.industry.fetch.client.CostIndexProvider;
import com.aba.industry.invention.InventionCalculator;
import com.aba.industry.manufacturing.ManufacturingCalculator;
import com.aba.industry.model.Activity;
import com.aba.industry.model.BuildCalculationResult;
import com.aba.industry.model.Decryptor;
import com.aba.industry.model.InventionCalculationResult;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;
import com.aba.industry.overhead.OverheadCalculator;
import com.aba.industry.service.IndustryCalculationService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Getter
@Setter
public class IndustryCalculationServiceImpl implements IndustryCalculationService {
    private static final Logger logger = LoggerFactory.getLogger( IndustryCalculationService.class );

    @Autowired
    private BuildOrBuyConfigurationService buildOrBuyService;

    @Autowired
    private BuildRequirementsProvider buildReqProvider;

    @Autowired
    private CostIndexProvider costIndexProvider;

    @Autowired
    private InventionCalculator inventionCalc;

    @Autowired
    private ManufacturingCalculator manufacturingCalc;

    @Autowired
    private OverheadConfigurationService overheadService;

    @Autowired
    private OverheadCalculator overheadCalculator;

    @Override
    public BuildCalculationResult calculateBuildCosts ( String systemName, Long outputTypeId ) {
        IndustrySkillConfiguration industrySkills = new IndustrySkillConfiguration();
        industrySkills.setAdvancedIndustrySkillLevel( 5 );
        industrySkills.setAdvancedIndustrySkillLevelMultiplier( 0.0 );
        industrySkills.setIndustrySkillLevel( 5 );
        industrySkills.setIndustrySkillLevelMultiplier( 0.0 );
        industrySkills.setPreference( ConfigurationType.PREFERED );

        InventionSkillConfiguration inventionSkills = new InventionSkillConfiguration();
        inventionSkills.setDatacoreOneSkillLevel( 3 );
        inventionSkills.setDatacoreOneSkillLevelMultiplier( 0.0 );
        inventionSkills.setDatacoreTwoSkillLevel( 3 );
        inventionSkills.setDatacoreTwoSkillLevelMultiplier( 0.0 );
        inventionSkills.setEncryptionSkillLevel( 4 );
        inventionSkills.setEncryptionSkillLevelMultiplier( 0.0 );
        inventionSkills.setPreference( ConfigurationType.PREFERED );
        return this.calculateBuildCosts( systemName, outputTypeId, industrySkills, inventionSkills, 10, 20, null, true,
                                         false );
    }

    @Override
    //TODO: me and te should be determined by already existing BP's or from the output of invention process with a decryptor
    //however, version one of this will be for speculation and will assume defaults
    //TODO: Refactor these inputs to be contained inside a BuildCalculationConfiguration or some such
    public BuildCalculationResult calculateBuildCosts (
            String systemName,
            Long outputTypeId,
            IndustrySkillConfiguration industrySkills,
            InventionSkillConfiguration inventionSkills,
            Integer meLevel,
            Integer teLevel,
            Decryptor decryptor,
            boolean findCurrentPrices,
            boolean useBuildOrBuyConfigurations )
    {
        BuildCalculationResult result = new BuildCalculationResult();

        //TODO: Tax Rate should be determined by builders standing with station holder
        Double taxRate = 0.0d;


        BlueprintData blueprintData = null;
        SystemCostIndexes costIndexes;
        try {
            blueprintData = buildReqProvider.getBlueprintData( outputTypeId );
            costIndexes = costIndexProvider.getSystemCostIndexes( systemName );
        }
        catch ( IOException e ) {
            logger.error( "Error while retrieving build requirements or cost indexes", e );
            throw new ApplicationException( e );
        }
        SalaryConfiguration salaryConfiguration = this.overheadService.getSalaryConfiguration();
        FreightConfiguration freightConfiguration = this.overheadService.getFreightConfiguration();

        InventionCalculationResult inventionCalculationResult = null;
        if ( !blueprintData.getBlueprintDetails()
                           .getTechLevel()
                           .equals( 1 ) )
        {
            inventionCalculationResult = this.inventionCalc.calculateInventionCosts( costIndexes, taxRate,
                                                                                     blueprintData, decryptor,
                                                                                     inventionSkills );
            inventionCalculationResult.setSalaryCost(
                    overheadCalculator.getSalary( Activity.INVENTION, inventionCalculationResult.getSeconds() ) );
        }

        result = this.manufacturingCalc.calculateBuildCost( costIndexes, taxRate,
                                                            blueprintData,
                                                            meLevel, teLevel,
                                                            industrySkills );

        result.setInventionResult( inventionCalculationResult );
        result.setSalaryCost( overheadCalculator.getSalary( Activity.MANUFACTURING, result.getSeconds() ) );

        return result;
    }
}
