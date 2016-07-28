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

package com.aba.data;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * A simple json super type which will insert a javaType attribute into the json string so
 * deserialization is easier and can be more dynamic on the receiving end.
 *
 * @author Marq Aideron <marq.aideron@gmail.com>
 */
@JsonTypeInfo( use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "javaType" )
public abstract class TypedJsonMessage {

}
