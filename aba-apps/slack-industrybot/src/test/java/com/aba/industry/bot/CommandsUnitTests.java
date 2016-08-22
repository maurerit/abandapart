/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.bot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by maurerit on 8/7/16.
 */
@RunWith( MockitoJUnitRunner.class )
public class CommandsUnitTests {
    @Test
    public void testBuildSleipnir ( ) {
        String message = "@123JKLAKI: calculate Sleipnir?";

        CalculateCommands command = CalculateCommands.findCommand( message );

        Assert.assertEquals( CalculateCommands.CALCULATE_BUILD_BASIC, command );

        String[] calculateWhat = command.getInterestingSegments( message );

        Assert.assertEquals( 1, calculateWhat.length );
        Assert.assertEquals( "Sleipnir", calculateWhat[0] );
    }

    @Test
    public void testNoMatchFound ( ) {
        String message = "@12890JKJKL: calculate  ";

        CalculateCommands command = CalculateCommands.findCommand( message );

        Assert.assertNull( command );
    }

    @Test
    public void testBuildSleipnirUsingBuyPrices ( ) {
        String message = "@1234JKLJAKFLD: calculate Sleipnir using buy prices?";

        CalculateCommands command = CalculateCommands.findCommand( message );

        Assert.assertEquals( CalculateCommands.CALCULATE_BUILD_SPECIFY_PROCURE_TYPE, command );

        String[] segments = command.getInterestingSegments( message );

        Assert.assertEquals( 2, segments.length );
        Assert.assertEquals( "Sleipnir", segments[0] );
        Assert.assertEquals( "buy", segments[1] );
    }

    @Test
    public void testBuildSleipnirWithSellPrices ( ) {
        String message = "@1234JKALFJKDSL: calculate Sleipnir with sell";

        CalculateCommands command = CalculateCommands.findCommand( message );

        Assert.assertEquals( CalculateCommands.CALCULATE_BUILD_SPECIFY_PROCURE_TYPE, command );

        String[] segments = command.getInterestingSegments( message );

        Assert.assertEquals( 2, segments.length );
        Assert.assertEquals( "Sleipnir", segments[0] );
        Assert.assertEquals( "sell", segments[1] );
    }

    @Test
    public void testBuild150mmRailgunII ( ) {
        String message = "@1234JKALFJKDSL: calculate 150mm Railgun II";

        CalculateCommands command = CalculateCommands.findCommand( message );

        Assert.assertEquals( CalculateCommands.CALCULATE_BUILD_BASIC, command );

        String[] segments = command.getInterestingSegments( message );

        Assert.assertEquals( 1, segments.length );
        Assert.assertEquals( "150mm Railgun II", segments[0] );
    }

    @Test
    public void testBuildLegionDefenseDashAdaptiveAugmenter ( ) {
        String message = "@1234JKALFJKDSL: calculate Legion Defense - Adaptive Augmenter";

        CalculateCommands command = CalculateCommands.findCommand( message );

        Assert.assertEquals( CalculateCommands.CALCULATE_BUILD_BASIC, command );

        String[] segments = command.getInterestingSegments( message );

        Assert.assertEquals( 1, segments.length );
        Assert.assertEquals( "Legion Defense - Adaptive Augmenter", segments[0] );
    }
}
