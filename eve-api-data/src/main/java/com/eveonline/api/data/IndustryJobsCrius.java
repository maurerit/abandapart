package com.eveonline.api.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "apiindustryjobscrius")
public class IndustryJobsCrius
{
    private int jobID;
    private int installerId;
    private String installerName;
    private int facilityId;
    private int solarSystemId;
    private String solarSystemName;
    private int stationId;
    private int activityId;
    private int blueprintId;
    private int blueprintTypeId;
    private String blueprintTypeName;
    private int blueprintLocationId;
    private int outputLocationId;
    private int runs;
    private double cost;
    private int teamId;
    private int licensedRuns;
    private double probability;
    private int productTypeId;
    private String productTypeName;
    private int status;
    private int timeInSeconds;
    private Date startDate;
    private Date endDate;
    private Date pauseDate;
    private Date completedDate;
    private int completedCharacterId;
    private int successfulRuns;
    private int corporationId;

    @Id
    public int getJobID() {
        return jobID;
    }
    public int getInstallerId() {
        return installerId;
    }
    public String getInstallerName() {
        return installerName;
    }
    public int getFacilityId() {
        return facilityId;
    }
    public int getSolarSystemId() {
        return solarSystemId;
    }
    public String getSolarSystemName() {
        return solarSystemName;
    }
    public int getStationId() {
        return stationId;
    }
    public int getActivityId() {
        return activityId;
    }
    public int getBlueprintId() {
        return blueprintId;
    }
    public int getBlueprintTypeId() {
        return blueprintTypeId;
    }
    public String getBlueprintTypeName() {
        return blueprintTypeName;
    }
    public int getBlueprintLocationId() {
        return blueprintLocationId;
    }
    public int getOutputLocationId() {
        return outputLocationId;
    }
    public int getRuns() {
        return runs;
    }
    public double getCost() {
        return cost;
    }
    public int getTeamId() {
        return teamId;
    }
    public int getLicensedRuns() {
        return licensedRuns;
    }
    public double getProbability() {
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
    public int getCompletedCharacterId() {
        return completedCharacterId;
    }
    public int getSuccessfulRuns() {
        return successfulRuns;
    }
    public int getCorporationId() {
        return corporationId;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }
    public void setInstallerId(int installerId) {
        this.installerId = installerId;
    }
    public void setInstallerName(String installerName) {
        this.installerName = installerName;
    }
    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }
    public void setSolarSystemId(int solarSystemId) {
        this.solarSystemId = solarSystemId;
    }
    public void setSolarSystemName(String solarSystemName) {
        this.solarSystemName = solarSystemName;
    }
    public void setStationId(int stationId) {
        this.stationId = stationId;
    }
    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
    public void setBlueprintId(int blueprintId) {
        this.blueprintId = blueprintId;
    }
    public void setBlueprintTypeId(int blueprintTypeId) {
        this.blueprintTypeId = blueprintTypeId;
    }
    public void setBlueprintTypeName(String blueprintTypeName) {
        this.blueprintTypeName = blueprintTypeName;
    }
    public void setBlueprintLocationId(int blueprintLocationId) {
        this.blueprintLocationId = blueprintLocationId;
    }
    public void setOutputLocationId(int outputLocationId) {
        this.outputLocationId = outputLocationId;
    }
    public void setRuns(int runs) {
        this.runs = runs;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
    public void setLicensedRuns(int licensedRuns) {
        this.licensedRuns = licensedRuns;
    }
    public void setProbability(double probability) {
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
    public void setCompletedCharacterId(int completedCharacterId) {
        this.completedCharacterId = completedCharacterId;
    }
    public void setSuccessfulRuns(int successfulRuns) {
        this.successfulRuns = successfulRuns;
    }
    public void setCorporationId(int corporationId) {
        this.corporationId = corporationId;
    }
}
