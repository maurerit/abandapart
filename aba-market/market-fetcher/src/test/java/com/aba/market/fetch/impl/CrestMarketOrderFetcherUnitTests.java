/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.market.fetch.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.devfleet.crest.CrestService;
import org.devfleet.crest.model.CrestMarketBulkOrder;
import org.devfleet.crest.model.CrestMarketOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by maurerit on 7/27/2016.
 */
@RunWith( MockitoJUnitRunner.class )
public class CrestMarketOrderFetcherUnitTests {
    @InjectMocks
    CrestMarketOrderFetcher crestMarketOrderFetcher;
    @Mock
    CrestService            crestService;
    List<CrestMarketOrder>     sleipnirData;
    List<CrestMarketBulkOrder> allOrders;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setupData ( ) throws IOException
    {
        InputStream sleipnirDataIS = CrestMarketOrderFetcherUnitTests.class.getResourceAsStream(
                "/CrestMarketWithDataForSleipnirs.json" );
        InputStream allMarketData = CrestMarketOrderFetcherUnitTests.class.getResourceAsStream(
                "/CrestMarketAlotOfOrders.json" );

        TypeFactory typeFactory = mapper.getTypeFactory();
        CollectionType marketOrderType = typeFactory.constructCollectionType( List.class, CrestMarketOrder.class );
        CollectionType marketBulkOrderType = typeFactory.constructCollectionType( List.class,
                                                                                  CrestMarketBulkOrder.class );
        sleipnirData = mapper.readValue( sleipnirDataIS, marketOrderType );
        allOrders = mapper.readValue( allMarketData, marketBulkOrderType );
    }

    @Test
    public void testGetSomeResults ( )
    {
        Mockito.when( crestService.getMarketOrders( 0l, "sell", 22444 ) )
               .thenReturn( sleipnirData );

        Double lowestPrice = crestMarketOrderFetcher.getLowestSellPrice( 0l, 60008494l, 22444 );

        Assert.assertNotNull( lowestPrice );
    }

    @Test
    public void testGetNoData ( )
    {
        Mockito.when( crestService.getMarketOrders( 0l, "sell", 22444 ) )
               .thenReturn( sleipnirData );

        Double lowestPrice = crestMarketOrderFetcher.getLowestSellPrice( 0l, 10, 12345 );

        Assert.assertNull( lowestPrice );
    }

    @Test
    public void testPriceForQuantity ( ) throws IOException
    {
        Mockito.when( crestService.getMarketOrders( 0l, "sell", 22444 ) )
               .thenReturn( sleipnirData );

        Double priceForQuantity = crestMarketOrderFetcher.getPriceForQuantity( 0l, 60008494l, 22444, 3 );

        Assert.assertEquals( 347988897.55, priceForQuantity, 0.01 );
    }

//    @Test
//    public void testLotsOfOrders ( ) throws IOException
//    {
//        Mockito.when( crestService.getAllMarketOrders( 0l ) )
//               .thenReturn( allOrders );
//
//        List<CrestMarketBulkOrder> retrievedOrders = crestService.getAllMarketOrders( 0l );
//
//        Assert.assertFalse( retrievedOrders.isEmpty() );
//    }
}
