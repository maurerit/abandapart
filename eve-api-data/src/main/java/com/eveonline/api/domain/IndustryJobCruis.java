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
@Table( name = "apiindustryjobscrius" )
public class IndustryJobCruis {
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

    @Id
    public long getJobId ( ) {
        return jobId;
    }

    public void setJobId ( long jobId ) {
        this.jobId = jobId;
    }

    public long getInstallerId ( ) {
        return installerId;
    }

    public void setInstallerId ( long installerId ) {
        this.installerId = installerId;
    }

    public String getInstallerName ( ) {
        return installerName;
    }

    public void setInstallerName ( String installerName ) {
        this.installerName = installerName;
    }

    public long getFacilityId ( ) {
        return facilityId;
    }

    public void setFacilityId ( long facilityId ) {
        this.facilityId = facilityId;
    }

    public int getSolarSystemId ( ) {
        return solarSystemId;
    }

    public void setSolarSystemId ( int solarSystemId ) {
        this.solarSystemId = solarSystemId;
    }

    public String getSolarSystemName ( ) {
        return solarSystemName;
    }

    public void setSolarSystemName ( String solarSystemName ) {
        this.solarSystemName = solarSystemName;
    }

    public long getStationId ( ) {
        return stationId;
    }

    public void setStationId ( long stationId ) {
        this.stationId = stationId;
    }

    public int getActivityId ( ) {
        return activityId;
    }

    public void setActivityId ( int activityId ) {
        this.activityId = activityId;
    }

    public long getBlueprintId ( ) {
        return blueprintId;
    }

    public void setBlueprintId ( long blueprintId ) {
        this.blueprintId = blueprintId;
    }

    public int getBlueprintTypeId ( ) {
        return blueprintTypeId;
    }

    public void setBlueprintTypeId ( int blueprintTypeId ) {
        this.blueprintTypeId = blueprintTypeId;
    }

    public String getBlueprintTypeName ( ) {
        return blueprintTypeName;
    }

    public void setBlueprintTypeName ( String blueprintTypeName ) {
        this.blueprintTypeName = blueprintTypeName;
    }

    public long getBlueprintLocationId ( ) {
        return blueprintLocationId;
    }

    public void setBlueprintLocationId ( long blueprintLocationId ) {
        this.blueprintLocationId = blueprintLocationId;
    }

    public long getOutputLocationId ( ) {
        return outputLocationId;
    }

    public void setOutputLocationId ( long outputLocationId ) {
        this.outputLocationId = outputLocationId;
    }

    public int getRuns ( ) {
        return runs;
    }

    public void setRuns ( int runs ) {
        this.runs = runs;
    }

    public BigDecimal getCost ( ) {
        return cost;
    }

    public void setCost ( BigDecimal cost ) {
        this.cost = cost;
    }

    public long getTeamId ( ) {
        return teamId;
    }

    public void setTeamId ( long teamId ) {
        this.teamId = teamId;
    }

    public int getLicensedRuns ( ) {
        return licensedRuns;
    }

    public void setLicensedRuns ( int licensedRuns ) {
        this.licensedRuns = licensedRuns;
    }

    public BigDecimal getProbability ( ) {
        return probability;
    }

    public void setProbability ( BigDecimal probability ) {
        this.probability = probability;
    }

    public int getProductTypeId ( ) {
        return productTypeId;
    }

    public void setProductTypeId ( int productTypeId ) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName ( ) {
        return productTypeName;
    }

    public void setProductTypeName ( String productTypeName ) {
        this.productTypeName = productTypeName;
    }

    public int getStatus ( ) {
        return status;
    }

    public void setStatus ( int status ) {
        this.status = status;
    }

    public int getTimeInSeconds ( ) {
        return timeInSeconds;
    }

    public void setTimeInSeconds ( int timeInSeconds ) {
        this.timeInSeconds = timeInSeconds;
    }

    public Date getStartDate ( ) {
        return startDate;
    }

    public void setStartDate ( Date startDate ) {
        this.startDate = startDate;
    }

    public Date getEndDate ( ) {
        return endDate;
    }

    public void setEndDate ( Date endDate ) {
        this.endDate = endDate;
    }

    public Date getPauseDate ( ) {
        return pauseDate;
    }

    public void setPauseDate ( Date pauseDate ) {
        this.pauseDate = pauseDate;
    }

    public Date getCompletedDate ( ) {
        return completedDate;
    }

    public void setCompletedDate ( Date completedDate ) {
        this.completedDate = completedDate;
    }

    public long getCompletedCharacterId ( ) {
        return completedCharacterId;
    }

    public void setCompletedCharacterId ( long completedCharacterId ) {
        this.completedCharacterId = completedCharacterId;
    }

    public int getSuccessfulRuns ( ) {
        return successfulRuns;
    }

    public void setSuccessfulRuns ( int successfulRuns ) {
        this.successfulRuns = successfulRuns;
    }

    public long getCorporationId ( ) {
        return corporationId;
    }

    public void setCorporationId ( long corporationId ) {
        this.corporationId = corporationId;
    }
}
