/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the License.
 */

package com.aba.eveonline.crest.impl;

import com.aba.eveonline.crest.repo.impl.CrestRegionRepository;
import org.devfleet.crest.retrofit.CrestClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by maurerit on 7/25/16.
 */
public class RegionRepositoryImplUnitTests {
    private CrestRegionRepository repo = new CrestRegionRepository();

    @Before
    public void setupCrestClient ( ) throws IOException {
        repo.setCrestService( CrestClient.TQ()
                                         .build()
                                         .fromDefault() );
    }

    @Test
    public void testGetTheForgeId ( ) {
        long theForgeId = repo.findRegionId( "The Forge" );
        Assert.assertEquals( theForgeId, 10000002l );
    }

    @Test
    public void testGetVenalId ( ) {
        long venalId = repo.findRegionId( "Venal" );
        Assert.assertEquals( venalId, 10000015l );
    }

    @Test( expected = java.util.NoSuchElementException.class )
    public void testGetUnkownRegionId ( ) {
        long whereAreWeId = repo.findRegionId( "Where are we?" );
    }
}
