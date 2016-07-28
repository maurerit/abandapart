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

package com.abandapart.industry.fetch;

import org.devfleet.crest.CrestService;
import org.devfleet.crest.model.CrestMarketBulkOrder;
import org.devfleet.crest.retrofit.CrestClient;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CrestTests {
    private static final Logger LOG = LoggerFactory.getLogger( CrestTests.class );

    private static CrestService service;

    @BeforeClass
    public static void setupCREST ( ) throws Exception {
        service = CrestClient.TQ()
                             .build()
                             .fromDefault();
    }


    @Test
    @Ignore
    public void testGetAllMarketOrders ( ) {
        NumberFormat format = new DecimalFormat( "###,###.##" );
        final long start = System.currentTimeMillis();
        final List<CrestMarketBulkOrder> bo = service.getAllMarketOrders( 10000002 );
        final long end = System.currentTimeMillis();
        Assert.assertFalse( bo.isEmpty() );

        final long mapStart = System.currentTimeMillis();
        Map<Long, Double> valueMap = bo
                .stream()
                .filter(
                        cmbo -> cmbo.isBuyOrder() == false )
                .collect(
                        Collectors.groupingBy(
                                CrestMarketBulkOrder::getType, Collectors.summingDouble(
                                        cmbo -> cmbo.getVolume() * cmbo.getPrice() ) ) );
        Double totalSellValue = bo
                .stream()
                .filter( cmbo -> cmbo.isBuyOrder() == false )
//                .filter(cmbo -> cmbo.getType() == 3074)
                .mapToDouble( cmbo -> cmbo.getVolume() * cmbo.getPrice() )
                .sum();
        final long mapEnd = System.currentTimeMillis();

        valueMap.entrySet()
                .stream()
                .forEach( f -> {
                    LOG.info( "Item: " + f.getKey() + " has total value of: " + format.format( f.getValue() ) );
                } );

        LOG.info( "It took " + ( end - start ) + " milliseconds to get all forge orders" );
        LOG.info( "Total value on The Forge Market = " + format.format( totalSellValue ) );
        LOG.info( "Received " + bo.size() + " market orders" );
        LOG.info( "Mapping took " + ( mapEnd - mapStart ) + " miliseconds" );
    }

}
