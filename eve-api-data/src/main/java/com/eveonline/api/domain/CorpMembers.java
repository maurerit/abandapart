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
import java.util.Date;

@Entity
@Table( name = "apicorpmembers" )
public class CorpMembers {
    private long   characterId;
    private String name;
    private Date   startDateTime;
    private long   baseId;
    private String base;
    private String title;
    private long   corporationId;

    @Id
    public long getCharacterId ( ) {
        return characterId;
    }

    public void setCharacterId ( long characterId ) {
        this.characterId = characterId;
    }

    public String getName ( ) {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public Date getStartDateTime ( ) {
        return startDateTime;
    }

    public void setStartDateTime ( Date startDateTime ) {
        this.startDateTime = startDateTime;
    }

    public long getBaseId ( ) {
        return baseId;
    }

    public void setBaseId ( long baseId ) {
        this.baseId = baseId;
    }

    public String getBase ( ) {
        return base;
    }

    public void setBase ( String base ) {
        this.base = base;
    }

    public String getTitle ( ) {
        return title;
    }

    public void setTitle ( String title ) {
        this.title = title;
    }

    public long getCorporationId ( ) {
        return corporationId;
    }

    public void setCorporationId ( long corporationId ) {
        this.corporationId = corporationId;
    }
}
