package com.aba.market.fetch.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.devfleet.crest.CrestService;
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
 * Created by mm66053 on 7/27/2016.
 */
@RunWith( MockitoJUnitRunner.class )
public class CrestMarketOrderFetcherUnitTests {
    @InjectMocks
    CrestMarketOrderFetcher crestMarketOrderFetcher;
    @Mock
    CrestService            crestService;
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
}
