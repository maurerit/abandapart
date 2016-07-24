/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.eveonline.api.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "apicorps" )
public class Corporation {
    private long   corporationId;
    private String corporationName;
    private int    characterId;
    private String characterName;
    private String keyId;

    @Id
    public long getCorporationId ( ) {
        return corporationId;
    }

    public void setCorporationId ( long corporationID ) {
        this.corporationId = corporationID;
    }

    public String getCorporationName ( ) {
        return corporationName;
    }

    public void setCorporationName ( String corporationName ) {
        this.corporationName = corporationName;
    }

    public int getCharacterId ( ) {
        return characterId;
    }

    public void setCharacterId ( int characterId ) {
        this.characterId = characterId;
    }

    public String getCharacterName ( ) {
        return characterName;
    }

    public void setCharacterName ( String characterName ) {
        this.characterName = characterName;
    }

    public String getKeyId ( ) {
        return keyId;
    }

    public void setKeyId ( String keyId ) {
        this.keyId = keyId;
    }
}
