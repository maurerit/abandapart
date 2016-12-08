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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maurerit on 10/28/16.
 */
@XmlRootElement( name = "characterSkills" )
public class CharacterSkillsForExcel {
    private List<CharacterSkillsForExcelRow> characterSkillsForExcelRows = new ArrayList<>();

    @XmlElement( name = "row" )
    public List<CharacterSkillsForExcelRow> getCharacterSkillsForExcelRows ( ) {
        return this.characterSkillsForExcelRows;
    }
}
