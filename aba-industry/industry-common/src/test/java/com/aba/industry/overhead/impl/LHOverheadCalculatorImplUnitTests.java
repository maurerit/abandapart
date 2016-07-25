package com.aba.industry.overhead.impl;

import com.aba.industry.model.FreightDetails;
import com.aba.industry.overhead.OverheadCalculator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by maurerit on 7/25/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class LHOverheadCalculatorImplUnitTests {
    private OverheadCalculator calculator = new LHOverheadCalculatorImpl();

    @Test
    public void testHalfABillionFreightFromJita ( ) {
        FreightDetails freightDetails = calculator.getFreightDetails("Jita", "Atreen", 500000000d);

        Assert.assertEquals(freightDetails.getCharge(), 13000000d, 0.01);
        Assert.assertEquals(freightDetails.getJumps().longValue(), 15l);
    }

    @Test
    public void testHalfABillionFreightFromAmarr ( ) {
        FreightDetails freightDetails = calculator.getFreightDetails("Amarr", "Atreen", 500000000d);

        Assert.assertEquals(freightDetails.getCharge(), 8500000d, 0.01);
        Assert.assertEquals(freightDetails.getJumps().longValue(), 9l);
    }
}
