/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.config.impl;

import com.aba.data.domain.config.BuildOrBuyConfiguration;
import com.aba.industry.config.BuildOrBuyConfigurationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by maurerit on 8/8/16.
 */
@RunWith( MockitoJUnitRunner.class )
public class MapBackedBuildOrBuyConfigurationServiceUnitTests {

    private BuildOrBuyConfigurationService service = new MapBackedBuildOrBuyConfigurationService();

    @Test
    public void testGetDefaultConfiguration ( ) {
        BuildOrBuyConfiguration result = service.findByTypeId( 1 );

        Assert.assertEquals( BuildOrBuyConfiguration.BuildOrBuy.BUY, result.getBuildOrBuy() );
    }

    @Test
    public void testSetThenGetBuildConfiguration ( ) {
        service.createOrUpdateBuildOrBuyConfiguration( new BuildOrBuyConfiguration( 1,
                                                                                    BuildOrBuyConfiguration
                                                                                            .BuildOrBuy.BUILD ) );
        BuildOrBuyConfiguration result = service.findByTypeId( 1 );

        Assert.assertEquals( new Integer( 1 ), result.getTypeId() );
        Assert.assertEquals( BuildOrBuyConfiguration.BuildOrBuy.BUILD, result.getBuildOrBuy() );
    }

    @Test
    public void testSetThenDeleteBuildConfiguration ( ) {
        service.createOrUpdateBuildOrBuyConfiguration( new BuildOrBuyConfiguration( 1,
                                                                                    BuildOrBuyConfiguration
                                                                                            .BuildOrBuy.BUILD ) );

        service.deleteBuildOrByConfiguration(
                new BuildOrBuyConfiguration( 1, BuildOrBuyConfiguration.BuildOrBuy.BUILD ) );
        BuildOrBuyConfiguration result = service.findByTypeId( 1 );

        Assert.assertEquals( BuildOrBuyConfiguration.BuildOrBuy.BUY, result.getBuildOrBuy() );
    }
}
