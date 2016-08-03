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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

/**
 * Created by maurerit on 8/2/16.
 */
@RunWith( MockitoJUnitRunner.class )
public class TypeNameToTypeIdProviderUnitTests {
    FuzzySteveService fuzzySteveService = new FuzzySteveService();

    @Test
    public void testGet150mmRailgunIITypeId ( ) throws IOException {
        Integer result = fuzzySteveService.getTypeIdForTypeName( "150mm Railgun II" );

        Assert.assertEquals( new Integer( 3074 ), result );
    }

    @Test
    public void testGetBadName ( ) throws IOException {
        Integer result = fuzzySteveService.getTypeIdForTypeName( "150mm Rail" );

        Assert.assertEquals( new Integer( -1 ), result );
    }
}
