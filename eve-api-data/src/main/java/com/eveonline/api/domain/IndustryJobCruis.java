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
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table( name = "apiindustryjobscrius" )
@Data
public class IndustryJobCruis {
    @Id
    private long       jobId;
    private long       installerId;
    private String     installerName;
    private long       facilityId;
    private int        solarSystemId;
    private String     solarSystemName;
    private long       stationId;
    private int        activityId;
    private long       blueprintId;
    private int        blueprintTypeId;
    private String     blueprintTypeName;
    private long       blueprintLocationId;
    private long       outputLocationId;
    private int        runs;
    private BigDecimal cost;
    private long       teamId;
    private int        licensedRuns;
    private BigDecimal probability;
    private int        productTypeId;
    private String     productTypeName;
    private int        status;
    private int        timeInSeconds;
    private Date       startDate;
    private Date       endDate;
    private Date       pauseDate;
    private Date       completedDate;
    private long       completedCharacterId;
    private int        successfulRuns;
    private long       corporationId;
}
