/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.eveonline.crest.impl;

import com.aba.eveonline.crest.repo.CrestItemTypeRepository;
import org.devfleet.crest.CrestService;
import org.devfleet.crest.model.CrestItem;
import org.devfleet.crest.retrofit.CrestClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

/**
 * Created by maurerit on 7/31/16.
 */
@RunWith( MockitoJUnitRunner.class )
public class CrestItemTypeRepositoryUnitTests {
    private CrestItemTypeRepository crestItemTypeRepository = new CrestItemTypeRepository();
    private CrestService crestService;

    @Before
    public void setupCrestService ( ) throws IOException {
        crestService = CrestClient.TQ()
                                  .build()
                                  .fromDefault();
        crestItemTypeRepository.setCrestService( crestService );
    }

    @Test
    public void testFindSliepnirName ( ) {
        CrestItem item = crestItemTypeRepository.getItemDetails( 22444 );

        Assert.assertEquals( item.getName(), "Sleipnir" );
    }
}
