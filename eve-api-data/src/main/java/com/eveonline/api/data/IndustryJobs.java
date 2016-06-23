package com.eveonline.api.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "apiindustryjobs")
public class IndustryJobs
{
    private int jobId;
    private int assemblyLineId;
    private int containerId;
    private int installedItemId;
    private int installedItemLocationId;
    private int installedItemQuantity;
    private int installedItemProductivityLevel;
    private int installedItemMaterialLevel;
    private int installedItemLicensedProductionRunsRemaining;
    private int outputLocationId;
    private int installerId;
    private int runs;
    private int licensedProductionRuns;
    private int installedInSolarSystemId;
    private int containerLocationId;
    private double materialMultiplier;
    private double charMaterialMultiplier;
    private double timeMultiplier;
    private double charTimeMultiplier;
    private int installedItemTypeId;
    private int outputTypeId;
    private int containerTypeId;
    private int installedItemCopy;
    private int completed;
    private int completedSuccessfully;
    private int successfulRuns;
    private int installedItemFlag;
    private int outputFlag;
    private int activityId;
    private int completedStatus;
    private Date installTime;
    private Date beginProductionTime;
    private Date endProductionTime;
    private Date pauseProductionTime;
    private int corporationId;

    @Id
    public int getJobId() {
        return jobId;
    }
    public int getAssemblyLineId() {
        return assemblyLineId;
    }
    public int getContainerId() {
        return containerId;
    }
    public int getInstalledItemId() {
        return installedItemId;
    }
    public int getInstalledItemLocationId() {
        return installedItemLocationId;
    }
    public int getInstalledItemQuantity() {
        return installedItemQuantity;
    }
    public int getInstalledItemProductivityLevel() {
        return installedItemProductivityLevel;
    }
    public int getInstalledItemMaterialLevel() {
        return installedItemMaterialLevel;
    }
    public int getInstalledItemLicensedProductionRunsRemaining() {
        return installedItemLicensedProductionRunsRemaining;
    }
    public int getOutputLocationId() {
        return outputLocationId;
    }
    public int getInstallerId() {
        return installerId;
    }
    public int getRuns() {
        return runs;
    }
    public int getLicensedProductionRuns() {
        return licensedProductionRuns;
    }
    public int getInstalledInSolarSystemId() {
        return installedInSolarSystemId;
    }
    public int getContainerLocationId() {
        return containerLocationId;
    }
    public double getMaterialMultiplier() {
        return materialMultiplier;
    }
    public double getCharMaterialMultiplier() {
        return charMaterialMultiplier;
    }
    public double getTimeMultiplier() {
        return timeMultiplier;
    }
    public double getCharTimeMultiplier() {
        return charTimeMultiplier;
    }
    public int getInstalledItemTypeId() {
        return installedItemTypeId;
    }
    public int getOutputTypeId() {
        return outputTypeId;
    }
    public int getContainerTypeId() {
        return containerTypeId;
    }
    public int getInstalledItemCopy() {
        return installedItemCopy;
    }
    public int getCompleted() {
        return completed;
    }
    public int getCompletedSuccessfully() {
        return completedSuccessfully;
    }
    public int getSuccessfulRuns() {
        return successfulRuns;
    }
    public int getInstalledItemFlag() {
        return installedItemFlag;
    }
    public int getOutputFlag() {
        return outputFlag;
    }
    public int getActivityId() {
        return activityId;
    }
    public int getCompletedStatus() {
        return completedStatus;
    }
    public Date getInstallTime() {
        return installTime;
    }
    public Date getBeginProductionTime() {
        return beginProductionTime;
    }
    public Date getPauseProductionTime() {
        return pauseProductionTime;
    }
    public Date getEndProductionTime() {
        return endProductionTime;
    }
    public int getCorporationId() {
        return corporationId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
    public void setAssemblyLineId(int assemblyLineId) {
        this.assemblyLineId = assemblyLineId;
    }
    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }
    public void setInstalledItemId(int installedItemId) {
        this.installedItemId = installedItemId;
    }
    public void setInstalledItemLocationId(int installedItemLocationId) {
        this.installedItemLocationId = installedItemLocationId;
    }
    public void setInstalledItemQuantity(int installedItemQuantity) {
        this.installedItemQuantity = installedItemQuantity;
    }
    public void setInstalledItemProductivityLevel(int installedItemProductivityLevel) {
        this.installedItemProductivityLevel = installedItemProductivityLevel;
    }
    public void setInstalledItemMaterialLevel(int installedItemMaterialLevel) {
        this.installedItemMaterialLevel = installedItemMaterialLevel;
    }
    public void setInstalledItemLicensedProductionRunsRemaining(int installedItemLicensedProductionRunsRemaining) {
        this.installedItemLicensedProductionRunsRemaining = installedItemLicensedProductionRunsRemaining;
    }
    public void setOutputLocationId(int outputLocationId) {
        this.outputLocationId = outputLocationId;
    }
    public void setInstallerId(int installerId) {
        this.installerId = installerId;
    }
    public void setRuns(int runs) {
        this.runs = runs;
    }
    public void setLicensedProductionRuns(int licensedProductionRuns) {
        this.licensedProductionRuns = licensedProductionRuns;
    }
    public void setInstalledInSolarSystemId(int installedInSolarSystemId) {
        this.installedInSolarSystemId = installedInSolarSystemId;
    }
    public void setContainerLocationId(int containerLocationId) {
        this.containerLocationId = containerLocationId;
    }
    public void setMaterialMultiplier(double materialMultiplier) {
        this.materialMultiplier = materialMultiplier;
    }
    public void setCharMaterialMultiplier(double charMaterialMultiplier) {
        this.charMaterialMultiplier = charMaterialMultiplier;
    }
    public void setTimeMultiplier(double timeMultiplier) {
        this.timeMultiplier = timeMultiplier;
    }
    public void setCharTimeMultiplier(double charTimeMultiplier) {
        this.charTimeMultiplier = charTimeMultiplier;
    }
    public void setInstalledItemTypeId(int installedItemTypeId) {
        this.installedItemTypeId = installedItemTypeId;
    }
    public void setOutputTypeId(int outputTypeId) {
        this.outputTypeId = outputTypeId;
    }
    public void setContainerTypeId(int containerTypeId) {
        this.containerTypeId = containerTypeId;
    }
    public void setInstalledItemCopy(int installedItemCopy) {
        this.installedItemCopy = installedItemCopy;
    }
    public void setCompleted(int completed) {
        this.completed = completed;
    }
    public void setCompletedSuccessfully(int completedSuccessfully) {
        this.completedSuccessfully = completedSuccessfully;
    }
    public void setSuccessfulRuns(int successfulRuns) {
        this.successfulRuns = successfulRuns;
    }
    public void setInstalledItemFlag(int installedItemFlag) {
        this.installedItemFlag = installedItemFlag;
    }
    public void setOutputFlag(int outputFlag) {
        this.outputFlag = outputFlag;
    }
    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
    public void setCompletedStatus(int completedStatus) {
        this.completedStatus = completedStatus;
    }
    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }
    public void setBeginProductionTime(Date beginProductionTime) {
        this.beginProductionTime = beginProductionTime;
    }
    public void setPauseProductionTime(Date pauseProductionTime) {
        this.pauseProductionTime = pauseProductionTime;
    }
    public void setEndProductionTime(Date endProductionTime) {
        this.endProductionTime = endProductionTime;
    }
    public void setCorporationId(int corporationId) {
        this.corporationId = corporationId;
    }
}
