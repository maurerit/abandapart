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
package com.aba.eveonline.model;

import lombok.Data;

@Data
public class I18NString {
    private String de;
    private String en;
    private String es;
    private String fr;
    private String it;
    private String ja;
    private String ru;
    private String zh;

    public boolean equals ( String value ) {
        if ( value == null ) {
            return false;
        }

        if ( value.equalsIgnoreCase(de) ) {
            return true;
        }

        if ( value.equalsIgnoreCase(en) ) {
            return true;
        }

        if ( value.equalsIgnoreCase(es) ) {
            return true;
        }

        if ( value.equalsIgnoreCase(fr) ) {
            return true;
        }

        if ( value.equalsIgnoreCase(it) ) {
            return true;
        }

        if ( value.equalsIgnoreCase(ja) ) {
            return true;
        }

        if ( value.equalsIgnoreCase(ru) ) {
            return true;
        }

        if ( value.equalsIgnoreCase(zh) ) {
            return true;
        }

        return false;
    }
}
