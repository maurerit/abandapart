/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.domain;

import com.aba.data.domain.Corporation;
import com.aba.industry.model.Skill;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Set;

/**
 * Created by maurerit on 10/5/16.
 */
@Data
public class Producer {
    @Id
    private String producerId;
    private Long   characterId;

    private Set<Skill> skills;

    private Long basedAtStation;

    @DBRef
    private Corporation corporation;
}
