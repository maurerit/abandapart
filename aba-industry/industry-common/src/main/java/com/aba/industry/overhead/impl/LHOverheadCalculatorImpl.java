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
import org.springframework.stereotype.Service;

@Service
public class LHOverheadCalculatorImpl implements OverheadCalculator {

    @Override
    public FreightDetails getFreightDetails ( String fromSystemName, String toSystemName, Double collateral ) {
        FreightDetails freightDetails = new FreightDetails();

        Double reward = 0d;

        switch ( fromSystemName ) {
            case "Jita":
                reward = collateral / 1000000000 * 26000000;
                freightDetails.setJumps( 15 );
                break;
            case "Amarr":
                reward = collateral / 1000000000 * 17000000;
                freightDetails.setJumps( 9 );
                break;
            default:
                switch ( toSystemName ) {
                    case "Jita":
                        reward = collateral / 1000000000 * 26000000;
                        freightDetails.setJumps( 15 );
                        break;
                    case "Amarr":
                        reward = collateral / 1000000000 * 17000000;
                        freightDetails.setJumps( 9 );
                        break;
                }
                break;
        }

        freightDetails.setCharge( reward );

        return freightDetails;
    }

    @Override
    public Double getSalary ( IndustryActivities industryActivities, Long seconds ) {
        switch ( industryActivities ) {
            case MANUFACTURING:
                return seconds.doubleValue() / 60 / 60 / 2 * 200000;
            case INVENTION:
                return seconds.doubleValue() / 60 / 60 / 40 * 200000;
        }
        return 0d;
    }
}
