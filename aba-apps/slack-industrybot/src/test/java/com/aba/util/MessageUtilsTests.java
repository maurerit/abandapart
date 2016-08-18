/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.util;

import com.aba.industry.bot.util.MessageUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by maurerit on 8/9/16.
 */
public class MessageUtilsTests {
    @Test
    public void testformatSecondsToDDHHMMSS_For1Day8Hours12MinutesAnd24Seconds ( ) {
        Long seconds = 115944l;

        String formatted = MessageUtils.formatSecondsToDDHHMMSS( seconds );

        Assert.assertEquals( "1 Day 08:12:24", formatted );
    }

    @Test
    public void testFormatSecondsToDDHHMMSS_For20Days16Hours6MinutesAnd2Seconds ( ) {
        Long seconds = 1785962l;

        String formatted = MessageUtils.formatSecondsToDDHHMMSS( seconds );

        Assert.assertEquals( "20 Days 16:06:02", formatted );
    }
}
