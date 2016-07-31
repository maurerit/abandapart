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
import com.aba.industry.ItemCost;
import com.aba.industry.model.ActivityMaterialWithCost;
import com.aba.industry.model.Decryptor;
import com.aba.industry.model.IndustryActivities;
import com.aba.industry.model.InventionCalculationResult;
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

public class InventionCalculatorImplUnitTests {

    private InventionCalculatorImpl calcImpl = new InventionCalculatorImpl();

    @Test
    public void testSleipnirWithNullDecryptor ( ) throws JsonParseException, JsonMappingException, IOException {
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
        Decryptor decryptor = null;
        InventionSkillConfiguration skillConfiguration = new InventionSkillConfiguration();

        skillConfiguration.setDatacoreOneSkillLevel( 3 );
        skillConfiguration.setDatacoreTwoSkillLevel( 3 );
        skillConfiguration.setEncryptionSkillLevel( 4 );

        for ( ActivityMaterialWithCost am : bpData.getActivityMaterials()
                                                  .get( IndustryActivities.INVENTION.getActivityId() ) ) {
            ItemCost ic = itemCosts.get( am.getTypeId()
                                           .intValue() );

            am.setCost( ic.getSell() );
            am.setAdjustedCost( ic.getAdjusted() );
        }

        for ( ActivityMaterialWithCost am : bpData.getActivityMaterials()
                                                  .get( IndustryActivities.MANUFACTURING.getActivityId() ) ) {
            ItemCost ic = itemCosts.get( am.getTypeId()
                                           .intValue() );

            am.setCost( ic.getSell() );
            am.setAdjustedCost( ic.getAdjusted() );
        }

        InventionCalculationResult result = calcImpl.calculateInventionCosts( costIndexes, 0.0d, bpData, decryptor,
                                                                              skillConfiguration );

        Assert.assertNotNull( "Result should not be null", result );
        Assert.assertEquals( 6808205.92, result.getCostPerSuccessfulInventionRun(), 0.01 );
    }

}
