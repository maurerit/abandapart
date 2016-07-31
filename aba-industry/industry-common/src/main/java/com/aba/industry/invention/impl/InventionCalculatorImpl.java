/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.invention.impl;

import com.aba.data.domain.config.InventionSkillConfiguration;
import com.aba.industry.invention.InventionCalculator;
import com.aba.industry.model.ActivityMaterialWithCost;
import com.aba.industry.model.Decryptor;
import com.aba.industry.model.IndustryActivities;
import com.aba.industry.model.InventionCalculationResult;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.BlueprintDetails;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author maurerit
 */
@Component
public class InventionCalculatorImpl implements InventionCalculator {
    @Override
    public InventionCalculationResult calculateInventionCosts (
            SystemCostIndexes costIndexes,
            Double taxRate,
            BlueprintData bpData,
            Decryptor decryptor,
            InventionSkillConfiguration skillConfiguration )
    {
        BlueprintDetails bpDetails = bpData.getBlueprintDetails();
        List<ActivityMaterialWithCost> inventionMaterials = bpData.getActivityMaterials()
                                                                  .get( IndustryActivities.INVENTION.getActivityId() );
        List<ActivityMaterialWithCost> productionMaterials = bpData.getActivityMaterials()
                                                                   .get( IndustryActivities.MANUFACTURING
                                                                                 .getActivityId() );

        InventionCalculationResult result = fillInResultDetails( bpData, decryptor, inventionMaterials,
                                                                 skillConfiguration,
                                                                 bpDetails );

        Double decryptorMultiplier = decryptor != null ? decryptor.getMultiplier() : 1;
        Double taxMultiplier = ( taxRate / 100 ) + 1;

        result.setProbability( this.getInventionChance( bpDetails, skillConfiguration, decryptorMultiplier ) );

        //Build up the cost of building the actual output, this will factor into determining the install cost of
        // invention
        Double adjustedManufacturingCost = 0d;
        for ( ActivityMaterialWithCost prodAdjustedCost : productionMaterials ) {
            adjustedManufacturingCost += prodAdjustedCost.getAdjustedCost() * prodAdjustedCost.getQuantity();
        }

        Double inventionRunCost = 0d;
        for ( ActivityMaterialWithCost inventionMaterial : inventionMaterials ) {
            inventionRunCost += inventionMaterial.getCost() * inventionMaterial.getQuantity();
        }

        //The run cost for the manufacturing portion of this invention job
        adjustedManufacturingCost *= 0.02 * costIndexes.getCostIndexes()
                                                       .get( IndustryActivities.INVENTION.getActivityId() ) * taxMultiplier;

        Double blueprintCopyCost = bpData.getBlueprintDetails()
                                         .getPrecursorAdjustedPrice() *
                costIndexes.getCostIndexes()
                           .get( IndustryActivities.INVENTION.getActivityId() ) *
                .02;

        result.setInstallationFees( adjustedManufacturingCost );
        result.setInstallationTax( adjustedManufacturingCost * .1 );
        result.setInventionRunCost( inventionRunCost );
        result.setBlueprintCopyCost( blueprintCopyCost );

        Double costPerSuccessfulInventionRun =
                (
                        result.getInstallationFees() +
                                result.getInstallationTax() +
                                inventionRunCost +
                                result.getBlueprintCopyCost()
                ) /
                        (
                                result.getProbability() / 100
                        );

        result.setCostPerSuccessfulInventionRun( costPerSuccessfulInventionRun );

        return result;
    }

    private InventionCalculationResult fillInResultDetails ( BlueprintData bpData, Decryptor decryptor,
                                                             List<ActivityMaterialWithCost> amWithCost,
                                                             InventionSkillConfiguration skillConfiguration,
                                                             BlueprintDetails bpDetails )
    {
        InventionCalculationResult result = new InventionCalculationResult();

        result.setOutputTypeId( bpDetails.getProductTypeId() );
        result.setDecryptor( decryptor );
        result.setMaterialsWithCost( amWithCost );
        result.setRequiredSkills( bpData.getBlueprintSkills()
                                        .get( IndustryActivities.INVENTION.getActivityId() ) );
        result.setSkillConfiguration( skillConfiguration );
        result.setSeconds( bpData.getBlueprintDetails()
                                 .getTimesInSeconds()
                                 .get( IndustryActivities.INVENTION.getActivityId() ) );

        if ( decryptor == null ) {
            result.setResultingME( 2 );
            result.setResultingTE( 4 );
            result.setResultingRuns( bpDetails.getMaxProductionLimit() );
        }
        else {
            result.setResultingME( 2 + decryptor.getMe() );
            result.setResultingTE( 4 + decryptor.getTe() );
            result.setResultingRuns( bpDetails.getMaxProductionLimit() + decryptor.getRuns() );
        }
        return result;
    }

    private double getInventionChance ( BlueprintDetails bpDetails, InventionSkillConfiguration skillConfiguration,
                                        Double decryptorMultiplier )
    {
        return Math.min(
                ( ( bpDetails.getBaseProbability() * 100d ) *
                        ( 1 +
                                (
                                        (
                                                skillConfiguration.getDatacoreOneSkillLevel()
                                                                  .doubleValue() +
                                                        skillConfiguration.getDatacoreTwoSkillLevel()
                                                                          .doubleValue()
                                        ) /
                                                30d
                                ) +
                                (
                                        skillConfiguration.getEncryptionSkillLevel()
                                                          .doubleValue() /
                                                40d
                                )
                        ) ) *
                        decryptorMultiplier,
                100 );
    }
}
