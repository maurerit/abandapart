/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.abandapart.industry.fetch;

import com.aba.industry.fetch.client.impl.FuzzySteveService;
import com.aba.industry.model.fuzzysteve.ActivityMaterial;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.model.fuzzysteve.SystemCostIndexes;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FuzzySteveServiceUnitTests {

    private FuzzySteveService client = new FuzzySteveService();

    @Test
    public void testGetSleipnirBlueprintDetails ( ) throws IOException {
        BlueprintData bpData = client.getBlueprintData( 22444l );

        assertNotNull( "Blueprint Details should not be null", bpData );
        assertNotNull( bpData.getActivityMaterials() );
        assertNotNull( bpData.getBlueprintDetails() );
        assertNotNull( bpData.getBlueprintSkills() );
        assertNotNull( bpData.getDecryptors() );
        assertNotNull( bpData.getRequestedId() );
        assertTrue( bpData.getRequestedId() > 0 );

        for ( ActivityMaterial material : bpData.getDecryptors() ) {
            assertNotNull( "Decryptor name should not be null", material.getName() );
            assertNotNull( "Decryptor typeId should not be null", material.getTypeId() );
        }
    }

    @Test
    public void testGetAtreenCostIndexes ( ) throws IOException {
        SystemCostIndexes costIndexes = client.getSystemCostIndexes( "Atreen" );

        assertNotNull( costIndexes );
        assertNotNull( costIndexes.getSolarSystemName() );
        assertNotNull( costIndexes.getSolarSystemId() );
        assertNotNull( costIndexes.getCostIndexes() );
    }

}
