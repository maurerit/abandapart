/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.character;

import com.aba.api.endpoint.CharacterEndpoints;
import com.aba.api.endpoint.EveApiKeyEndpoints;
import com.aba.character.model.CharacterSkillsForExcel;
import com.aba.character.model.CharacterSkillsForExcelRow;
import com.aba.data.domain.api.ApiKey;
import com.aba.data.domain.api.ApiKeyDetail;
import com.aba.data.domain.api.Character;
import com.aba.data.domain.api.Skill;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maurerit on 10/28/16.
 */
@RestController
@ConfigurationProperties( prefix = "aba.producer.skills" )
@Getter
public class ProducerSkills {
    @Autowired
    private EveApiKeyEndpoints eveApiKeyEndpoints;

    @Autowired
    private CharacterEndpoints characterEndpoints;

    private List<String> characters = new ArrayList<>();

    private List<String> allowedKeys = new ArrayList<>();

    @RequestMapping( value = "/producer/skills/excel/{key}", produces = MediaType.APPLICATION_XML_VALUE )
    public CharacterSkillsForExcel getProducerSkillsForExcel ( @PathVariable( "key" ) String key ) {
        if ( !allowedKeys.contains( key ) ) {
            throw new IllegalArgumentException( "Who are you again?" );
        }

        CharacterSkillsForExcel result = new CharacterSkillsForExcel();

        List<ApiKey> apiKeys = eveApiKeyEndpoints.listAll();

        Map<String, Long> characterNameToIdMap = new HashMap<>();

        for ( ApiKey apiKey : apiKeys ) {
            ApiKeyDetail apiKeyDetail = eveApiKeyEndpoints.findByKeyId( apiKey.getKeyId() );

            for ( Character character : apiKeyDetail.getCharacters() ) {
                if ( characters.contains( character.getCharacterName() ) ) {
                    characterNameToIdMap.put( character.getCharacterName(), character.getCharacterId() );
                }
            }
        }

        for ( Map.Entry<String, Long> entry : characterNameToIdMap.entrySet() ) {
            List<Skill> skills = characterEndpoints.getSkills( entry.getValue() );

            for ( Skill skill : skills ) {
                CharacterSkillsForExcelRow row = new CharacterSkillsForExcelRow();
                row.setCharacter( entry.getKey() );
                row.setSkill( skill.getTypeName() );
                row.setLevel( skill.getLevel() );

                result.getCharacterSkillsForExcelRows()
                      .add( row );
            }
        }

        return result;
    }
}
