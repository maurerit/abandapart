package com.abandapart.industry.price;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.devfleet.crest.CrestService;
import org.devfleet.crest.model.CrestDictionary;
import org.devfleet.crest.model.CrestMarketBulkOrder;
import org.devfleet.crest.retrofit.CrestClient;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrestTests {
	private static final Logger LOG = LoggerFactory.getLogger(CrestTests.class);

	private static CrestService service;

    @BeforeClass
    public static void setupCREST() throws Exception {
        service = CrestClient.TQ().build().fromDefault();
    }

	
	@Test
    public void testGetAllMarketOrders ( ) {
        NumberFormat format = new DecimalFormat("###,###.##");
        final long start = System.currentTimeMillis();
        final List<CrestMarketBulkOrder> bo = service.getAllOrders(10000002);
        final long end = System.currentTimeMillis();
        Assert.assertFalse(bo.isEmpty());

        final long mapStart = System.currentTimeMillis();
        Map<Long, Double> valueMap = bo
                .stream()
                .filter(
                        cmbo -> cmbo.isBuyOrder() == false)
                .collect(
                        Collectors.groupingBy(
                                CrestMarketBulkOrder::getType, Collectors.summingDouble(
                                        cmbo -> cmbo.getVolume() * cmbo.getPrice())));
        Double totalSellValue = bo
                .stream()
                .filter(cmbo -> cmbo.isBuyOrder() == false)
//                .filter(cmbo -> cmbo.getType() == 3074)
                .mapToDouble(cmbo -> cmbo.getVolume() * cmbo.getPrice()).sum();
        final long mapEnd = System.currentTimeMillis();

        valueMap.entrySet().stream().forEach(f -> { LOG.info("Item: " + f.getKey() + " has total value of: " + format.format(f.getValue())); });

        LOG.info("It took " + (end - start) + " milliseconds to get all forge orders");
        LOG.info("Total value on The Forge Market = " + format.format(totalSellValue));
        LOG.info("Received " + bo.size() + " market orders");
        LOG.info("Mapping took " + (mapEnd - mapStart) + " miliseconds");
    }

}
