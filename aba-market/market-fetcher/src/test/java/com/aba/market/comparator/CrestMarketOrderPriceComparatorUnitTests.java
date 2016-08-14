/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.market.comparator;

import com.aba.market.fetch.impl.CrestMarketOrderFetcherUnitTests;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.devfleet.crest.model.CrestMarketOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by maurerit on 7/28/2016.
 */
@RunWith( MockitoJUnitRunner.class )
public class CrestMarketOrderPriceComparatorUnitTests {
    List<CrestMarketOrder> sleipnirData;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setupData ( ) throws IOException
    {
        InputStream sleipnirDataIS = CrestMarketOrderFetcherUnitTests.class.getResourceAsStream(
                "/CrestMarketWithDataForSleipnirs.json" );

        TypeFactory typeFactory = mapper.getTypeFactory();
        CollectionType type = typeFactory.constructCollectionType( List.class, CrestMarketOrder.class );
        sleipnirData = mapper.readValue( sleipnirDataIS, type );
    }

    @Test
    public void testSortSomeSleipnirs ( )
    {
        List<CrestMarketOrder> sleipnirsSorted = sleipnirData.stream()
                                                             .sorted( new CrestMarketOrderPriceAscendingComparator() )
                                                             .collect( Collectors.toList() );

        Assert.assertEquals( 4595177550l, sleipnirsSorted.get( 0 )
                                                         .getId() );
    }
}
