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

package com.aba.industry.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A unit test just to illustrate the usage of deserializing a json object that has a property
 * within it that specifies what the java class it implements is.
 *
 * @author maurerit
 */
public class SerializeAndDeserialzeUsingJacksonTests {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void test ( ) throws IOException, ClassNotFoundException {
        SampleDataType sdt = new SampleDataType();
        sdt.setId( "jfkdajfldkafklsdjflafadsfjkadlsfjasl" );
        sdt.setQuantity( 200 );
        sdt.setAmount( 50000.01d );

        Map<String, String> keyValues = new HashMap<>();
        keyValues.put( "Key1", "Value1" );
        keyValues.put( "Key2", "Value2" );

        sdt.setKeyValues( keyValues );

        String json = objectMapper.writeValueAsString( sdt );

        System.out.println( json );

        JsonNode jsonNode = objectMapper.readTree( json );

        String type = jsonNode.get( "javaType" )
                              .asText();

        Class<?> cls = Class.forName( type );

        Object o = objectMapper.convertValue( jsonNode, cls );

        System.out.println( o.getClass() );
    }

}
