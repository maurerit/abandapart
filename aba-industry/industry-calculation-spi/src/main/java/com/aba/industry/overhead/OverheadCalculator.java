/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.overhead;

import com.aba.industry.model.FreightDetails;
import com.aba.industry.model.IndustryActivities;

public interface OverheadCalculator {
    FreightDetails getFreightDetails ( String fromSystemName, String toSystemName, Double collateral );

    /**
     * Convenience method for {@link com.aba.data.domain.config.ConfigurationType#PREFERED} of the
     * {@link IndustryActivities}
     *
     * @param industryActivities The activity being performed which needs salary calculated
     * @param seconds            The number of seconds it takes to perform the activity
     * @return A double representing the amount in isk to paid to the individual performing the task
     */
    Double getSalary ( IndustryActivities industryActivities, Long seconds );
}
