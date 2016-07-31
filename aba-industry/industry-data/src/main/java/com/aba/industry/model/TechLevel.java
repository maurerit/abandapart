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
 * Created by maurerit on 7/30/16.
 */
@Getter
public enum TechLevel {
    TECH_ONE( "standard", 1 ),
    LOOTED( "pirate", 1 ),
    ADVANCED( "tech 2 and 3", 2 );

    private String  representation;
    private Integer numerical;

    TechLevel ( String representation, Integer numerical ) {
        this.representation = representation;
        this.numerical = numerical;
    }
}
