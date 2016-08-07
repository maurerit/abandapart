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

import com.aba.industry.fetch.service.impl.StaticDataExportBlueprintYamlService;
import com.aba.industry.model.IndustryActivities;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

/**
 * Created by maurerit on 7/30/16.
 */
@RunWith( MockitoJUnitRunner.class )
public class StaticDataExportBlueprintYamlServiceUnitTests {
    StaticDataExportBlueprintYamlService staticDataExportBlueprintYamlService;

    @Before
    public void setup ( ) throws IOException {
        staticDataExportBlueprintYamlService = new StaticDataExportBlueprintYamlService();
    }

    @Test
    public void getSleipnirBlueprintData ( ) throws IOException {
        BlueprintData bpData = staticDataExportBlueprintYamlService.getBlueprintData( 22444 );

        Assert.assertNotNull( bpData );
        Assert.assertEquals( bpData.getBlueprintDetails()
                                   .getTimesInSeconds()
                                   .get( IndustryActivities.MANUFACTURING.getActivityId() )
                                   .longValue(), 300000l );
        Assert.assertNotNull( bpData.getActivityMaterials()
                                    .get( IndustryActivities.INVENTION.getActivityId() ) );
    }

    @Test
    public void testGetAllBlueprints ( ) {
        List<BlueprintData> result = staticDataExportBlueprintYamlService.getAllBlueprints();

        Assert.assertFalse( result.isEmpty() );
    }
}
