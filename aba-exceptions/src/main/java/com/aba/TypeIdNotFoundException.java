/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba;

import lombok.Getter;

/**
 * Created by maurerit on 8/9/16.
 */
@Getter
public class TypeIdNotFoundException extends Exception {
    private String typeName;

    public TypeIdNotFoundException ( String typeName, String message ) {
        super( message );
        this.typeName = typeName;
    }
}
