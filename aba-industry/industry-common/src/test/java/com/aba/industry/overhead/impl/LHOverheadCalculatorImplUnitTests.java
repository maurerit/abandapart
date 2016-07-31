/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.overhead.impl;

import com.aba.industry.model.FreightDetails;
import com.aba.industry.model.IndustryActivities;
import com.aba.industry.overhead.OverheadCalculator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by maurerit on 7/25/2016.
 */
@RunWith( MockitoJUnitRunner.class )
public class LHOverheadCalculatorImplUnitTests {
    private OverheadCalculator calculator = new LHOverheadCalculatorImpl();

    @Test
    public void testHalfABillionFreightFromJita ( ) {
        FreightDetails freightDetails = calculator.getFreightDetails( "Jita", "Atreen", 500000000d );

        Assert.assertEquals( freightDetails.getCharge(), 13000000d, 0.01 );
        Assert.assertEquals( freightDetails.getJumps()
                                           .longValue(), 15l );
    }

    @Test
    public void testHalfABillionFreightFromAmarr ( ) {
        FreightDetails freightDetails = calculator.getFreightDetails( "Amarr", "Atreen", 500000000d );

        Assert.assertEquals( freightDetails.getCharge(), 8500000d, 0.01 );
        Assert.assertEquals( freightDetails.getJumps()
                                           .longValue(), 9l );
    }

    @Test
    public void testInventionCalculationResults ( )
    {
        Double result = calculator.getSalary( IndustryActivities.INVENTION, 2000l );

        Assert.assertEquals( 2777.77, result, 0.01 );
    }

    @Test
    public void testManufacturingCalculationResults ( )
    {
        Double result = calculator.getSalary( IndustryActivities.MANUFACTURING, 20000l );

        Assert.assertEquals( 555555.55, result, 0.01 );
    }
}
