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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void printMemoryStuff ( ) {
        int mb = 1024*1024;

        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();

        runtime.gc();

        System.out.println("##### Heap utilization statistics [MB] #####");

        //Print used memory
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        //Print free memory
        System.out.println("Free Memory:"
                + runtime.freeMemory() / mb);

        //Print total available memory
        System.out.println("Total Memory:" + runtime.totalMemory() / mb);

        //Print Maximum available memory
        System.out.println("Max Memory:" + runtime.maxMemory() / mb);
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
        Assert.assertEquals( new Integer( 1 ), bpData.getBlueprintDetails()
                                                     .getInventionResultingRuns() );
    }

    @Test
    public void testGetAllBlueprints ( ) {
        List<BlueprintData> result = staticDataExportBlueprintYamlService.getAllBlueprints();

        Assert.assertFalse( result.isEmpty() );
    }

    @Test
    public void testGet150mmRailgunI ( ) throws JsonProcessingException
    {
        BlueprintData bpData = staticDataExportBlueprintYamlService.getBlueprintData( 565 );

        ObjectMapper mapper = new ObjectMapper(  );

        String resultStr = mapper.writeValueAsString( bpData );
        System.out.println(bpData);
        System.out.println(resultStr);

        Assert.assertNotNull( bpData.getBlueprintDetails() );
        Assert.assertNull( bpData.getBlueprintDetails()
                                 .getPrecursorTypeId() );
        Assert.assertEquals( null, bpData.getBlueprintDetails()
                                         .getInventionResultingRuns() );
    }

    @Test
    public void testGetProteus ( ) throws JsonProcessingException {
        BlueprintData bpData = staticDataExportBlueprintYamlService.getBlueprintData( 29988 );

        ObjectMapper mapper = new ObjectMapper(  );

        String resultStr = mapper.writeValueAsString( bpData );
        System.out.println(bpData);
        System.out.println(resultStr);

        Assert.assertNotNull( bpData.getBlueprintDetails() );
        Assert.assertNotNull( bpData.getBlueprintDetails() );
        Assert.assertNotNull( bpData.getBlueprintDetails()
                                    .getBaseProbability() );
        Assert.assertEquals( new Integer( 20 ), bpData.getBlueprintDetails()
                                                      .getInventionResultingRuns() );
    }
}
