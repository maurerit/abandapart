package com.eveonline.api.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "apiindustryjobscrius", schema = "eve")
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

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public int getInstallerId() {
        return installerId;
    }

    public void setInstallerId(int installerId) {
        this.installerId = installerId;
    }

    public String getInstallerName() {
        return installerName;
    }

    public void setInstallerName(String installerName) {
        this.installerName = installerName;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public int getSolarSystemId() {
        return solarSystemId;
    }

    public void setSolarSystemId(int solarSystemId) {
        this.solarSystemId = solarSystemId;
    }

    public String getSolarSystemName() {
        return solarSystemName;
    }

    public void setSolarSystemName(String solarSystemName) {
        this.solarSystemName = solarSystemName;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getBlueprintId() {
        return blueprintId;
    }

    public void setBlueprintId(int blueprintId) {
        this.blueprintId = blueprintId;
    }

    public int getBlueprintTypeId() {
        return blueprintTypeId;
    }

    public void setBlueprintTypeId(int blueprintTypeId) {
        this.blueprintTypeId = blueprintTypeId;
    }

    public String getBlueprintTypeName() {
        return blueprintTypeName;
    }

    public void setBlueprintTypeName(String blueprintTypeName) {
        this.blueprintTypeName = blueprintTypeName;
    }

    public int getBlueprintLocationId() {
        return blueprintLocationId;
    }

    public void setBlueprintLocationId(int blueprintLocationId) {
        this.blueprintLocationId = blueprintLocationId;
    }

    public int getOutputLocationId() {
        return outputLocationId;
    }

    public void setOutputLocationId(int outputLocationId) {
        this.outputLocationId = outputLocationId;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getLicensedRuns() {
        return licensedRuns;
    }

    public void setLicensedRuns(int licensedRuns) {
        this.licensedRuns = licensedRuns;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(int timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getPauseDate() {
        return pauseDate;
    }

    public void setPauseDate(Date pauseDate) {
        this.pauseDate = pauseDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public int getCompletedCharacterId() {
        return completedCharacterId;
    }

    public void setCompletedCharacterId(int completedCharacterId) {
        this.completedCharacterId = completedCharacterId;
    }

    public int getSuccessfulRuns() {
        return successfulRuns;
    }

    public void setSuccessfulRuns(int successfulRuns) {
        this.successfulRuns = successfulRuns;
    }

    public int getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(int corporationId) {
        this.corporationId = corporationId;
    }
}
