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
import org.devfleet.crest.CrestService;
import org.devfleet.crest.model.CrestMarketOrder;
import org.devfleet.crest.model.CrestMarketPrice;
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
 * Created by maurerit on 7/29/16.
 */
@RunWith( MockitoJUnitRunner.class )
public class CrestMarketPriceFetcherUnitTests {
    private List<CrestMarketOrder>     sleipnirData;
    private List<CrestMarketPrice>     prices;
    @InjectMocks
    private CrestMarketPriceFetcher    marketPriceFetcher;
    @Mock
    private CrestService               crestService;
    @Mock
    private MarketOrderFetcher         marketOrderFetcher;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup ( ) throws IOException {
        InputStream sleipnirDataIS = CrestMarketOrderFetcherUnitTests.class.getResourceAsStream(
                "/CrestMarketWithDataForSleipnirs.json" );
        InputStream marketPricesIS = CrestMarketOrderFetcherUnitTests.class.getResourceAsStream(
                "/MarketPricesWithSleipnirInIt.json" );

        TypeFactory typeFactory = mapper.getTypeFactory();
        CollectionType marketOrderType = typeFactory.constructCollectionType( List.class, CrestMarketOrder.class );
        CollectionType marketPricesType = typeFactory.constructCollectionType( List.class, CrestMarketPrice.class );
        sleipnirData = mapper.readValue( sleipnirDataIS, marketOrderType );

        prices = mapper.readValue( marketPricesIS, marketPricesType );
    }

    @Test
    public void testGetSomeResults ( )
    {
        Mockito.when( crestService.getMarketOrders( 0l, "sell", 22444 ) )
               .thenReturn( sleipnirData );
        Mockito.when( marketOrderFetcher.getMarketSellOrders( 0L, 22444 ) )
               .thenReturn( sleipnirData );

        Double lowestPrice = marketPriceFetcher.getLowestSellPrice( 0l, 30002187L, 22444 );

        Assert.assertNotNull( lowestPrice );
    }

    @Test
    public void testGetNoData ( )
    {
        Mockito.when( crestService.getMarketOrders( 0l, "sell", 22444 ) )
               .thenReturn( sleipnirData );

        Double lowestPrice = marketPriceFetcher.getLowestSellPrice( 0l, 10, 12345 );

        Assert.assertNull( lowestPrice );
    }

    @Test
    public void testThatWeGetAValue ( ) {
        Mockito.when( crestService.getAllMarketPrices() )
               .thenReturn( prices );
        Double sleipnirAdjustedPrice = marketPriceFetcher.getAdjustedPrice( 22444 );

        Assert.assertNotNull( sleipnirAdjustedPrice );
        Assert.assertNotEquals( 0d, sleipnirAdjustedPrice, 0.01 );
    }
}
