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

import com.aba.data.domain.config.ConfigurationType;
import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.industry.ItemCost;
import com.aba.industry.invention.impl.InventionCalculatorImplUnitTests;
import com.aba.industry.manufacturing.ManufacturingCalculator;
import com.aba.industry.model.ActivityMaterialWithCost;
import com.aba.industry.model.BuildCalculationResult;
import com.aba.industry.model.IndustryActivities;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ManufacturingCalculatorImplUnitTests {

    private ManufacturingCalculator calc = new ManufacturingCalculatorImpl();

    @Test
    public void testSleipnirBuildCosts ( ) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream bpDetailsIS = InventionCalculatorImplUnitTests.class.getResourceAsStream(
                "/testSleipnirWithNullDecryptor-BlueprintDetails.json" );
        InputStream costIndexesIS = InventionCalculatorImplUnitTests.class.getResourceAsStream(
                "/testSleipnirWithNullDecryptor-CostIndexes.json" );
        InputStream itemCostIS = InventionCalculatorImplUnitTests.class.getResourceAsStream(
                "/testSleipnirWithNullDecryptor-ItemCosts.json" );

        TypeFactory typeFactory = mapper.getTypeFactory();
        MapType mapType = typeFactory.constructMapType( HashMap.class, Integer.class, ItemCost.class );

        Map<Integer, ItemCost> itemCosts = mapper.readValue( itemCostIS, mapType );

        SystemCostIndexes costIndexes = mapper.readValue( costIndexesIS, SystemCostIndexes.class );
        BlueprintData bpData = mapper.readValue( bpDetailsIS, BlueprintData.class );

        IndustrySkillConfiguration industrySkills = new IndustrySkillConfiguration();

        industrySkills.setAdvancedIndustrySkillLevel( 5 );
        industrySkills.setIndustrySkillLevel( 5 );
        industrySkills.setPreference( ConfigurationType.EXCEPTIONAL );

        for ( ActivityMaterialWithCost am : bpData.getActivityMaterials()
                                                  .get( IndustryActivities.MANUFACTURING.getActivityId() ) ) {
            ItemCost ic = itemCosts.get( am.getTypeId()
                                           .intValue() );

            am.setCost( ic.getSell() );
            am.setAdjustedCost( ic.getAdjusted() );
        }

        BuildCalculationResult result = calc.calculateBuildCost( costIndexes, 1d, bpData, 2, 4, industrySkills );

        Assert.assertEquals( 312429715.96, result.getMaterialCost(), 0.01 );
        Assert.assertEquals( 20036519.59, result.getInstallationFees(), 0.01 );
        Assert.assertEquals( 2003651.95, result.getInstallationTax(), 0.01 );
        Assert.assertEquals( result.getProductId(), 22444l );
    }

}
