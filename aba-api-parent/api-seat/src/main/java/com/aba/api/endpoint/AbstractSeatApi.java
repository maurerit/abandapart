/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.api.endpoint;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

/**
 * Created by maurerit on 10/28/16.
 */
@ConfigurationProperties( "aba.api.seat" )
@Setter
@Getter
public abstract class AbstractSeatApi {
    @Value( "aba.api.seat.apiKey" )
    protected String apiKey;
    @Value( "aba.api.seat.apiUrl" )
    protected String apiUrl;

    protected HttpEntity<String> getExpectedHeaders ( ) {
        HttpHeaders headers = new HttpHeaders();
        headers.set( "X-Token", apiKey );
        return new HttpEntity<>( "parameters", headers );
    }
}
