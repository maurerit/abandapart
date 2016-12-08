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

import com.aba.data.domain.api.ApiKey;
import com.aba.data.domain.api.ApiKeyDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by maurerit on 10/28/16.
 */
public class SeatEveApiKeyEndpoints extends AbstractSeatApi implements EveApiKeyEndpoints {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<ApiKey> listAll ( ) {
        List<ApiKey> result;

        HttpEntity<String> entity = super.getExpectedHeaders();
        ResponseEntity<ApiKey[]> responseEntity = restTemplate.exchange( super.getApiUrl() + "/key", HttpMethod.GET,
                                                                         entity, ApiKey[].class );
        result = Arrays.asList( responseEntity.getBody() );

        return result;
    }

    @Override
    public ApiKeyDetail findByKeyId ( Long keyId ) {
        HttpEntity<String> entity = super.getExpectedHeaders();
        ResponseEntity<ApiKeyDetail> responseEntity = restTemplate.exchange( super.getApiUrl() + "/key/" + keyId,
                                                                             HttpMethod.GET, entity,
                                                                             ApiKeyDetail.class );

        return responseEntity.getBody();
    }
}
