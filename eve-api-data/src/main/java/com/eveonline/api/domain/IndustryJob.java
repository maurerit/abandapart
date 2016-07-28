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
@Table( name = "apiindustryjobs" )
@Data
public class IndustryJob {
    @Id
    private long       jobId;
    private long       assemblyLineId;
    private long       containerId;
    private long       installedItemId;
    private long       installedItemLocationId;
    private long       installedItemQuantity;
    private long       installedItemProductivityLevel;
    private long       installedItemMaterialLevel;
    private long       installedItemLicensedProductionRunsRemaining;
    private long       outputLocationId;
    private long       installerId;
    private int        runs;
    private int        licensedProductionRuns;
    private long       installedInSolarSystemId;
    private long       containerLocationId;
    private BigDecimal materialMultiplier;
    private BigDecimal charMaterialMultiplier;
    private BigDecimal timeMultiplier;
    private BigDecimal charTimeMultiplier;
    private int        installedItemTypeId;
    private int        outputTypeId;
    private int        containerTypeId;
    private int        installedItemCopy;
    private int        completed;
    private int        completedSuccessfully;
    private int        successfulRuns;
    private int        installedItemFlag;
    private int        outputFlag;
    private int        activityId;
    private int        completedStatus;
    private Date       installTime;
    private Date       beginProductionTime;
    private Date       endProductionTime;
    private Date       pauseProductionTime;
    private long       corporationId;
}
