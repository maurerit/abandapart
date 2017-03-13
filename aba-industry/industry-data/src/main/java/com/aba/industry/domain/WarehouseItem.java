/*
 * Copyright 2017 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.MappedSuperclass;

/**
 * Created by maurerit on 7/24/16.
 */
//@Entity
//@MappedSuperclass
@Data
public class WarehouseItem {
    @Id
    private Long   typeId;
    private Long   entityId;
    private Long   quantity;
    private Double cost;
    private Long   regionId;
    private Long   constelationId;
    private Long   solarSystemId;
    private Long   stationId;
}
