package com.eveonline.api.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "apiindustryjobscrius")
public class IndustryJobCruis
{
    private long jobId;
    private long installerId;
    private String installerName;
    private long facilityId;
    private int solarSystemId;
    private String solarSystemName;
    private long stationId;
    private int activityId;
    private long blueprintId;
    private int blueprintTypeId;
    private String blueprintTypeName;
    private long blueprintLocationId;
    private long outputLocationId;
    private int runs;
    private BigDecimal cost;
    private long teamId;
    private int licensedRuns;
    private BigDecimal probability;
    private int productTypeId;
    private String productTypeName;
    private int status;
    private int timeInSeconds;
    private Date startDate;
    private Date endDate;
    private Date pauseDate;
    private Date completedDate;
    private long completedCharacterId;
    private int successfulRuns;
    private long corporationId;

    @Id
    public long getJobId() {
        return jobId;
    }
    public long getInstallerId() {
        return installerId;
    }
    public String getInstallerName() {
        return installerName;
    }
    public long getFacilityId() {
        return facilityId;
    }
    public int getSolarSystemId() {
        return solarSystemId;
    }
    public String getSolarSystemName() {
        return solarSystemName;
    }
    public long getStationId() {
        return stationId;
    }
    public int getActivityId() {
        return activityId;
    }
    public long getBlueprintId() {
        return blueprintId;
    }
    public int getBlueprintTypeId() {
        return blueprintTypeId;
    }
    public String getBlueprintTypeName() {
        return blueprintTypeName;
    }
    public long getBlueprintLocationId() {
        return blueprintLocationId;
    }
    public long getOutputLocationId() {
        return outputLocationId;
    }
    public int getRuns() {
        return runs;
    }
    public BigDecimal getCost() {
        return cost;
    }
    public long getTeamId() {
        return teamId;
    }
    public int getLicensedRuns() {
        return licensedRuns;
    }
    public BigDecimal getProbability() {
        return probability;
    }
    public int getProductTypeId() {
        return productTypeId;
    }
    public String getProductTypeName() {
        return productTypeName;
    }
    public int getStatus() {
        return status;
    }
    public int getTimeInSeconds() {
        return timeInSeconds;
    }
    public Date getStartDate() {
        return startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public Date getPauseDate() {
        return pauseDate;
    }
    public Date getCompletedDate() {
        return completedDate;
    }
    public long getCompletedCharacterId() {
        return completedCharacterId;
    }
    public int getSuccessfulRuns() {
        return successfulRuns;
    }
    public long getCorporationId() {
        return corporationId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }
    public void setInstallerId(long installerId) {
        this.installerId = installerId;
    }
    public void setInstallerName(String installerName) {
        this.installerName = installerName;
    }
    public void setFacilityId(long facilityId) {
        this.facilityId = facilityId;
    }
    public void setSolarSystemId(int solarSystemId) {
        this.solarSystemId = solarSystemId;
    }
    public void setSolarSystemName(String solarSystemName) {
        this.solarSystemName = solarSystemName;
    }
    public void setStationId(long stationId) {
        this.stationId = stationId;
    }
    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
    public void setBlueprintId(long blueprintId) {
        this.blueprintId = blueprintId;
    }
    public void setBlueprintTypeId(int blueprintTypeId) {
        this.blueprintTypeId = blueprintTypeId;
    }
    public void setBlueprintTypeName(String blueprintTypeName) {
        this.blueprintTypeName = blueprintTypeName;
    }
    public void setBlueprintLocationId(long blueprintLocationId) {
        this.blueprintLocationId = blueprintLocationId;
    }
    public void setOutputLocationId(long  outputLocationId) {
        this.outputLocationId = outputLocationId;
    }
    public void setRuns(int runs) {
        this.runs = runs;
    }
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }
    public void setLicensedRuns(int licensedRuns) {
        this.licensedRuns = licensedRuns;
    }
    public void setProbability(BigDecimal probability) {
        this.probability = probability;
    }
    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }
    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void setTimeInSeconds(int timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public void setPauseDate(Date pauseDate) {
        this.pauseDate = pauseDate;
    }
    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }
    public void setCompletedCharacterId(long completedCharacterId) {
        this.completedCharacterId = completedCharacterId;
    }
    public void setSuccessfulRuns(int successfulRuns) {
        this.successfulRuns = successfulRuns;
    }
    public void setCorporationId(long corporationId) {
        this.corporationId = corporationId;
    }
}
