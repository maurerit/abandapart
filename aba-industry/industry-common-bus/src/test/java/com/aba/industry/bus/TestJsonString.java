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

package com.aba.industry.bus;

import com.aba.data.TypedJsonMessage;

public class TestJsonString extends TypedJsonMessage {
    public String contents;

    public TestJsonString ( ) { }

    public TestJsonString ( String contents ) {
        super();
        this.contents = contents;
    }

    @Override
    public int hashCode ( ) {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( contents == null ) ? 0 : contents.hashCode() );
        return result;
    }

    @Override
    public boolean equals ( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        TestJsonString other = (TestJsonString) obj;
        if ( contents == null ) {
            if ( other.contents != null ) {
                return false;
            }
        }
        else if ( !contents.equals( other.contents ) ) {
            return false;
        }
        return true;
    }
}
