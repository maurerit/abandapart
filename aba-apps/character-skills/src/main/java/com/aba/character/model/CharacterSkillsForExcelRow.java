/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.character.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by maurerit on 10/28/16.
 */
public class CharacterSkillsForExcelRow {
    private String  character;
    private String  skill;
    private Integer level;

    @XmlAttribute( name = "character" )
    public String getCharacter ( ) {
        return character;
    }

    public void setCharacter ( String character ) {
        this.character = character;
    }

    @XmlAttribute( name = "skill" )
    public String getSkill ( ) {
        return skill;
    }

    public void setSkill ( String skill ) {
        this.skill = skill;
    }

    @XmlAttribute( name = "level" )
    public Integer getLevel ( ) {
        return level;
    }

    public void setLevel ( Integer level ) {
        this.level = level;
    }
}
