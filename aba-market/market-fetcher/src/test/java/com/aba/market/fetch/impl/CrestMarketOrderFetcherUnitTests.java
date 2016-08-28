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
    private CrestMarketOrderFetcher    crestMarketOrderFetcher;
    @Mock
    private CrestService               crestService;
    private List<CrestMarketOrder>     sleipnirData;
    private List<CrestMarketBulkOrder> allOrders;
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
    public void testGetMarketSellOrdersReturnsSleipnirData ( ) {
        Mockito.when( crestService.getMarketOrders( 0l, "sell", 0l ) )
               .thenReturn( sleipnirData );

        List<CrestMarketOrder> orders = crestMarketOrderFetcher.getMarketSellOrders( 0l, 0l );

        Assert.assertEquals( sleipnirData, orders );
    }

    @Test
    public void testGetMarketBuyOrdersReturnsSleipnirData ( ) {
        Mockito.when( crestService.getMarketOrders( 0l, "buy", 0l ) )
               .thenReturn( sleipnirData );

        List<CrestMarketOrder> orders = crestMarketOrderFetcher.getMarketBuyOrders( 0l, 0l );

        Assert.assertEquals( sleipnirData, orders );
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
