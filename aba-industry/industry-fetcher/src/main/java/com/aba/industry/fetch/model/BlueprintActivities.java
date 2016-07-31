/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.fetch.model;

import com.aba.industry.model.IndustryActivities;
import lombok.Getter;

/**
 * Created by maurerit on 7/30/16.
 */
@Getter
public enum BlueprintActivities {
    copying( "copying", IndustryActivities.COPYING ),
    invention( "invention", IndustryActivities.INVENTION ),
    manufacturing( "manufacturing", IndustryActivities.MANUFACTURING ),
    research_material( "research_material", IndustryActivities.RESEARCH_MATERIAL ),
    research_time( "research_time", IndustryActivities.RESEARCH_TIME );

    private String             representation;
    private IndustryActivities industryActivity;

    BlueprintActivities ( String representation, IndustryActivities industryActivity ) {
        this.representation = representation;
        this.industryActivity = industryActivity;
    }
}
