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

import com.aba.market.fetch.MarketOrderFetcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
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
 * Created by maurerit on 8/27/16.
 */
@RunWith( MockitoJUnitRunner.class )
public class CrestMarketOrderSearcherUnitTests {
    @InjectMocks
    private CrestMarketOrderSearcher crestuMarketOrderSearcher;
    @Mock
    private MarketOrderFetcher       marketOrderFetcher;

    private List<CrestMarketOrder>     sleipnirData;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setupData ( ) throws IOException
    {
        InputStream sleipnirDataIS = CrestMarketOrderFetcherUnitTests.class.getResourceAsStream(
                "/CrestMarketForTestPriceForQuantity.json" );

        TypeFactory typeFactory = mapper.getTypeFactory();
        CollectionType marketOrderType = typeFactory.constructCollectionType( List.class, CrestMarketOrder.class );
        sleipnirData = mapper.readValue( sleipnirDataIS, marketOrderType );
    }


    @Test
    public void testPriceForQuantity ( ) throws IOException
    {
        Mockito.when( marketOrderFetcher.getMarketSellOrders( 0L, 22444 ) )
               .thenReturn( sleipnirData );

        Double priceForQuantity = crestuMarketOrderSearcher.getPriceForQuantity( 0L, 0L, 22444, 3 );

        Assert.assertEquals( 347988897.55, priceForQuantity, 0.01 );
    }
}
