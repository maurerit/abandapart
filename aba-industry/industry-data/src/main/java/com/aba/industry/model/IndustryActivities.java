/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.model;

import lombok.Getter;

/**
 * Static enumeration to hold all available industrial activities.
 *
 * @author maurerit
 */
@Getter
public enum IndustryActivities {
    COPYING( "Copying", 5/*?*/ ),
    RESEARCH_MATERIAL( "Research Material Efficiency", 4 ),
    RESEARCH_TIME( "Research Time Efficiency", 3 ),
    INVENTION( "Invention", 8 ),
    MANUFACTURING( "Manufacturing", 1 );

    private final String  activityName;
    private final Integer activityId;

    IndustryActivities ( String activityName, Integer activityId ) {
        this.activityName = activityName;
        this.activityId = activityId;
    }
}
