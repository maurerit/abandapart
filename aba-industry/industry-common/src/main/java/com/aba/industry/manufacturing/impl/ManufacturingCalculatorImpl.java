/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.manufacturing.impl;

import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.industry.manufacturing.ManufacturingCalculator;
import com.aba.industry.model.ActivityMaterialWithCost;
import com.aba.industry.model.BuildCalculationResult;
import com.aba.industry.model.IndustryActivities;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maurerit
 */
@Component
public class ManufacturingCalculatorImpl implements ManufacturingCalculator {
    private static final Logger logger = LoggerFactory.getLogger( ManufacturingCalculator.class );

    @Override
    public BuildCalculationResult calculateBuildCost (
            SystemCostIndexes costIndexes,
            Double taxMultiplier,
            BlueprintData bpData,
            Integer meLevel,
            Integer teLevel,
            IndustrySkillConfiguration industrySkills )
    {
        BuildCalculationResult result = new BuildCalculationResult();
        result.setSkillConfiguration( industrySkills );

    	/*
         * for (materialid in blueprintData['activityMaterials'][1]) {
         *   material=blueprintData['activityMaterials'][1][materialid];
         *   reducedquantity=material.quantity*(1-(me/100))*facilityme[facility]*(1-(teamMe/100));
    	 */

        List<ActivityMaterialWithCost> materialsReduced = new ArrayList<>();
        Double materialCost = 0d;
        Double runCost = 0d;
        for ( ActivityMaterialWithCost material : bpData.getActivityMaterials()
                                                        .get( IndustryActivities.MANUFACTURING.getActivityId() ) ) {
            ActivityMaterialWithCost materialReduced = new ActivityMaterialWithCost();

            materialReduced.setBlueprintTypeId( material.getBlueprintTypeId() );
            materialReduced.setCost( material.getCost() );
            materialReduced.setAdjustedCost( material.getAdjustedCost() );
            materialReduced.setName( material.getName() );
            materialReduced.setQuantity( Math.round( material.getQuantity() * ( 1 - ( meLevel.doubleValue() / 100 ) )
                                                     /* * facility.getFacilityMe()*/ ) );
            materialReduced.setSource( material.getSource() );
            materialReduced.setTypeId( material.getTypeId() );

            materialsReduced.add( materialReduced );

            materialCost += materialReduced.getCost() * materialReduced.getQuantity();
            runCost += materialReduced.getAdjustedCost() == null ? 0d : materialReduced.getAdjustedCost() * material
                    .getQuantity();
        }
        result.setMaterialCost( materialCost );
        result.setMaterialsWithCost( materialsReduced );
        runCost = ( runCost * costIndexes.getCostIndexes()
                                         .get( IndustryActivities.MANUFACTURING.getActivityId() ) ) * taxMultiplier;

        //buildTime=blueprintData.blueprintDetails.times[1]*(1-(te/100))*(1-((industry*4)/100))*(1-((aindustry*3)
        // /100))*facilityte[facility]*runs*dcmultiplier;
        Long buildTime = Math.round( bpData.getBlueprintDetails()
                                           .getTimesInSeconds()
                                           .get( IndustryActivities.MANUFACTURING.getActivityId() ) *
                                             ( 1 - ( teLevel.doubleValue() / 100d ) ) *
                                             ( 1 - ( ( industrySkills.getIndustrySkillLevel()
                                                                     .doubleValue() * 4 ) / 100d ) ) *
                                             ( 1 - ( ( industrySkills.getAdvancedIndustrySkillLevel()
                                                                     .doubleValue() * 3d ) / 100d ) ) );

        result.setInstallationFees( runCost );
        result.setInstallationTax( runCost * .1d );
        result.setSeconds( buildTime );
        result.setProductId( bpData.getBlueprintDetails()
                                   .getProductTypeId() );

        return result;
    }
}
