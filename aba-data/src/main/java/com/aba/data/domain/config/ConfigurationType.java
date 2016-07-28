/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the License.
 */

package com.aba.data.domain.config;

import lombok.Getter;

@Getter
public enum ConfigurationType {
    REQUIRED( 1, "Required" ),
    PREFERED( 2, "Prefered" ),
    EXCEPTIONAL( 3, "Exceptional" );

    private final int    rank;
    private final String name;

    ConfigurationType ( int rank, String name ) {
        this.rank = rank;
        this.name = name;
    }
}
