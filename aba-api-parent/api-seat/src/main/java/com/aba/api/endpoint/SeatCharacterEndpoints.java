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

import com.aba.data.domain.api.Skill;
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
public class SeatCharacterEndpoints extends AbstractSeatApi implements CharacterEndpoints {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Skill> getSkills ( Long characterId ) {
        List<Skill> result;

        HttpEntity<String> entity = super.getExpectedHeaders();
        ResponseEntity<Skill[]> responseEntity = restTemplate.exchange(
                super.getApiUrl() + "/character/skills/" + characterId, HttpMethod.GET, entity, Skill[].class );
        result = Arrays.asList( responseEntity.getBody() );

        return result;
    }
}
