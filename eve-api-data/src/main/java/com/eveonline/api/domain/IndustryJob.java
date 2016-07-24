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
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table( name = "apiindustryjobs" )
public class IndustryJob {
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

    @Id
    public long getJobId ( ) {
        return jobId;
    }

    public void setJobId ( long jobId ) {
        this.jobId = jobId;
    }

    public long getAssemblyLineId ( ) {
        return assemblyLineId;
    }

    public void setAssemblyLineId ( long assemblyLineId ) {
        this.assemblyLineId = assemblyLineId;
    }

    public long getContainerId ( ) {
        return containerId;
    }

    public void setContainerId ( long containerId ) {
        this.containerId = containerId;
    }

    public long getInstalledItemId ( ) {
        return installedItemId;
    }

    public void setInstalledItemId ( long installedItemId ) {
        this.installedItemId = installedItemId;
    }

    public long getInstalledItemLocationId ( ) {
        return installedItemLocationId;
    }

    public void setInstalledItemLocationId ( long installedItemLocationId ) {
        this.installedItemLocationId = installedItemLocationId;
    }

    public long getInstalledItemQuantity ( ) {
        return installedItemQuantity;
    }

    public void setInstalledItemQuantity ( long installedItemQuantity ) {
        this.installedItemQuantity = installedItemQuantity;
    }

    public long getInstalledItemProductivityLevel ( ) {
        return installedItemProductivityLevel;
    }

    public void setInstalledItemProductivityLevel ( long installedItemProductivityLevel ) {
        this.installedItemProductivityLevel = installedItemProductivityLevel;
    }

    public long getInstalledItemMaterialLevel ( ) {
        return installedItemMaterialLevel;
    }

    public void setInstalledItemMaterialLevel ( long installedItemMaterialLevel ) {
        this.installedItemMaterialLevel = installedItemMaterialLevel;
    }

    public long getInstalledItemLicensedProductionRunsRemaining ( ) {
        return installedItemLicensedProductionRunsRemaining;
    }

    public void setInstalledItemLicensedProductionRunsRemaining ( long installedItemLicensedProductionRunsRemaining ) {
        this.installedItemLicensedProductionRunsRemaining = installedItemLicensedProductionRunsRemaining;
    }

    public long getOutputLocationId ( ) {
        return outputLocationId;
    }

    public void setOutputLocationId ( long outputLocationId ) {
        this.outputLocationId = outputLocationId;
    }

    public long getInstallerId ( ) {
        return installerId;
    }

    public void setInstallerId ( long installerId ) {
        this.installerId = installerId;
    }

    public int getRuns ( ) {
        return runs;
    }

    public void setRuns ( int runs ) {
        this.runs = runs;
    }

    public int getLicensedProductionRuns ( ) {
        return licensedProductionRuns;
    }

    public void setLicensedProductionRuns ( int licensedProductionRuns ) {
        this.licensedProductionRuns = licensedProductionRuns;
    }

    public long getInstalledInSolarSystemId ( ) {
        return installedInSolarSystemId;
    }

    public void setInstalledInSolarSystemId ( long installedInSolarSystemId ) {
        this.installedInSolarSystemId = installedInSolarSystemId;
    }

    public long getContainerLocationId ( ) {
        return containerLocationId;
    }

    public void setContainerLocationId ( long containerLocationId ) {
        this.containerLocationId = containerLocationId;
    }

    public BigDecimal getMaterialMultiplier ( ) {
        return materialMultiplier;
    }

    public void setMaterialMultiplier ( BigDecimal materialMultiplier ) {
        this.materialMultiplier = materialMultiplier;
    }

    public BigDecimal getCharMaterialMultiplier ( ) {
        return charMaterialMultiplier;
    }

    public void setCharMaterialMultiplier ( BigDecimal charMaterialMultiplier ) {
        this.charMaterialMultiplier = charMaterialMultiplier;
    }

    public BigDecimal getTimeMultiplier ( ) {
        return timeMultiplier;
    }

    public void setTimeMultiplier ( BigDecimal timeMultiplier ) {
        this.timeMultiplier = timeMultiplier;
    }

    public BigDecimal getCharTimeMultiplier ( ) {
        return charTimeMultiplier;
    }

    public void setCharTimeMultiplier ( BigDecimal charTimeMultiplier ) {
        this.charTimeMultiplier = charTimeMultiplier;
    }

    public int getInstalledItemTypeId ( ) {
        return installedItemTypeId;
    }

    public void setInstalledItemTypeId ( int installedItemTypeId ) {
        this.installedItemTypeId = installedItemTypeId;
    }

    public int getOutputTypeId ( ) {
        return outputTypeId;
    }

    public void setOutputTypeId ( int outputTypeId ) {
        this.outputTypeId = outputTypeId;
    }

    public int getContainerTypeId ( ) {
        return containerTypeId;
    }

    public void setContainerTypeId ( int containerTypeId ) {
        this.containerTypeId = containerTypeId;
    }

    public int getInstalledItemCopy ( ) {
        return installedItemCopy;
    }

    public void setInstalledItemCopy ( int installedItemCopy ) {
        this.installedItemCopy = installedItemCopy;
    }

    public int getCompleted ( ) {
        return completed;
    }

    public void setCompleted ( int completed ) {
        this.completed = completed;
    }

    public int getCompletedSuccessfully ( ) {
        return completedSuccessfully;
    }

    public void setCompletedSuccessfully ( int completedSuccessfully ) {
        this.completedSuccessfully = completedSuccessfully;
    }

    public int getSuccessfulRuns ( ) {
        return successfulRuns;
    }

    public void setSuccessfulRuns ( int successfulRuns ) {
        this.successfulRuns = successfulRuns;
    }

    public int getInstalledItemFlag ( ) {
        return installedItemFlag;
    }

    public void setInstalledItemFlag ( int installedItemFlag ) {
        this.installedItemFlag = installedItemFlag;
    }

    public int getOutputFlag ( ) {
        return outputFlag;
    }

    public void setOutputFlag ( int outputFlag ) {
        this.outputFlag = outputFlag;
    }

    public int getActivityId ( ) {
        return activityId;
    }

    public void setActivityId ( int activityId ) {
        this.activityId = activityId;
    }

    public int getCompletedStatus ( ) {
        return completedStatus;
    }

    public void setCompletedStatus ( int completedStatus ) {
        this.completedStatus = completedStatus;
    }

    public Date getInstallTime ( ) {
        return installTime;
    }

    public void setInstallTime ( Date installTime ) {
        this.installTime = installTime;
    }

    public Date getBeginProductionTime ( ) {
        return beginProductionTime;
    }

    public void setBeginProductionTime ( Date beginProductionTime ) {
        this.beginProductionTime = beginProductionTime;
    }

    public Date getPauseProductionTime ( ) {
        return pauseProductionTime;
    }

    public void setPauseProductionTime ( Date pauseProductionTime ) {
        this.pauseProductionTime = pauseProductionTime;
    }

    public Date getEndProductionTime ( ) {
        return endProductionTime;
    }

    public void setEndProductionTime ( Date endProductionTime ) {
        this.endProductionTime = endProductionTime;
    }

    public long getCorporationId ( ) {
        return corporationId;
    }

    public void setCorporationId ( long corporationId ) {
        this.corporationId = corporationId;
    }
}
