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
                                                             .sorted( new CrestMarketOrderPriceComparator() )
                                                             .collect( Collectors.toList() );

        Assert.assertEquals( 4595177550l, sleipnirsSorted.get( 0 )
                                                         .getId() );
    }
}
