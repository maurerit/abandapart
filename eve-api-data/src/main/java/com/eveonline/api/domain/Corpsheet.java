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

package com.eveonline.api.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "apicorpsheet" )
@Data
public class Corpsheet {
    @Id
    private long   corporationId;
    private String corporationName;
    private String ticker;
    private long   ceoId;
    private String ceoName;
    private long   stationId;
    private String stationName;
    private String description;
    private String url;
    private long   allianceId;
    private int    taxRate;
    private int    memberCount;
    private int    memberLimit;
    private int    shares;
    private int    graphicId;
    private int    shape1;
    private int    shape2;
    private int    shape3;
    private int    color1;
    private int    color2;
    private int    color3;
}
