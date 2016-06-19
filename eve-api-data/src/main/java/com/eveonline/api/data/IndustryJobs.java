package com.eveonline.api.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "apiindustryjobs", schema = "eve")
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

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getAssemblyLineId() {
        return assemblyLineId;
    }

    public void setAssemblyLineId(int assemblyLineId) {
        this.assemblyLineId = assemblyLineId;
    }

    public int getContainerId() {
        return containerId;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }

    public int getInstalledItemId() {
        return installedItemId;
    }

    public void setInstalledItemId(int installedItemId) {
        this.installedItemId = installedItemId;
    }

    public int getInstalledItemLocationId() {
        return installedItemLocationId;
    }

    public void setInstalledItemLocationId(int installedItemLocationId) {
        this.installedItemLocationId = installedItemLocationId;
    }

    public int getInstalledItemQuantity() {
        return installedItemQuantity;
    }

    public void setInstalledItemQuantity(int installedItemQuantity) {
        this.installedItemQuantity = installedItemQuantity;
    }

    public int getInstalledItemProductivityLevel() {
        return installedItemProductivityLevel;
    }

    public void setInstalledItemProductivityLevel(int installedItemProductivityLevel) {
        this.installedItemProductivityLevel = installedItemProductivityLevel;
    }

    public int getInstalledItemMaterialLevel() {
        return installedItemMaterialLevel;
    }

    public void setInstalledItemMaterialLevel(int installedItemMaterialLevel) {
        this.installedItemMaterialLevel = installedItemMaterialLevel;
    }

    public int getInstalledItemLicensedProductionRunsRemaining() {
        return installedItemLicensedProductionRunsRemaining;
    }

    public void setInstalledItemLicensedProductionRunsRemaining(int installedItemLicensedProductionRunsRemaining) {
        this.installedItemLicensedProductionRunsRemaining = installedItemLicensedProductionRunsRemaining;
    }

    public int getOutputLocationId() {
        return outputLocationId;
    }

    public void setOutputLocationId(int outputLocationId) {
        this.outputLocationId = outputLocationId;
    }

    public int getInstallerId() {
        return installerId;
    }

    public void setInstallerId(int installerId) {
        this.installerId = installerId;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getLicensedProductionRuns() {
        return licensedProductionRuns;
    }

    public void setLicensedProductionRuns(int licensedProductionRuns) {
        this.licensedProductionRuns = licensedProductionRuns;
    }

    public int getInstalledInSolarSystemId() {
        return installedInSolarSystemId;
    }

    public void setInstalledInSolarSystemId(int installedInSolarSystemId) {
        this.installedInSolarSystemId = installedInSolarSystemId;
    }

    public int getContainerLocationId() {
        return containerLocationId;
    }

    public void setContainerLocationId(int containerLocationId) {
        this.containerLocationId = containerLocationId;
    }

    public double getMaterialMultiplier() {
        return materialMultiplier;
    }

    public void setMaterialMultiplier(double materialMultiplier) {
        this.materialMultiplier = materialMultiplier;
    }

    public double getCharMaterialMultiplier() {
        return charMaterialMultiplier;
    }

    public void setCharMaterialMultiplier(double charMaterialMultiplier) {
        this.charMaterialMultiplier = charMaterialMultiplier;
    }

    public double getTimeMultiplier() {
        return timeMultiplier;
    }

    public void setTimeMultiplier(double timeMultiplier) {
        this.timeMultiplier = timeMultiplier;
    }

    public double getCharTimeMultiplier() {
        return charTimeMultiplier;
    }

    public void setCharTimeMultiplier(double charTimeMultiplier) {
        this.charTimeMultiplier = charTimeMultiplier;
    }

    public int getInstalledItemTypeId() {
        return installedItemTypeId;
    }

    public void setInstalledItemTypeId(int installedItemTypeId) {
        this.installedItemTypeId = installedItemTypeId;
    }

    public int getOutputTypeId() {
        return outputTypeId;
    }

    public void setOutputTypeId(int outputTypeId) {
        this.outputTypeId = outputTypeId;
    }

    public int getContainerTypeId() {
        return containerTypeId;
    }

    public void setContainerTypeId(int containerTypeId) {
        this.containerTypeId = containerTypeId;
    }

    public int getInstalledItemCopy() {
        return installedItemCopy;
    }

    public void setInstalledItemCopy(int installedItemCopy) {
        this.installedItemCopy = installedItemCopy;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getCompletedSuccessfully() {
        return completedSuccessfully;
    }

    public void setCompletedSuccessfully(int completedSuccessfully) {
        this.completedSuccessfully = completedSuccessfully;
    }

    public int getSuccessfulRuns() {
        return successfulRuns;
    }

    public void setSuccessfulRuns(int successfulRuns) {
        this.successfulRuns = successfulRuns;
    }

    public int getInstalledItemFlag() {
        return installedItemFlag;
    }

    public void setInstalledItemFlag(int installedItemFlag) {
        this.installedItemFlag = installedItemFlag;
    }

    public int getOutputFlag() {
        return outputFlag;
    }

    public void setOutputFlag(int outputFlag) {
        this.outputFlag = outputFlag;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(int completedStatus) {
        this.completedStatus = completedStatus;
    }

    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public Date getBeginProductionTime() {
        return beginProductionTime;
    }

    public void setBeginProductionTime(Date beginProductionTime) {
        this.beginProductionTime = beginProductionTime;
    }

    public Date getPauseProductionTime() {
        return pauseProductionTime;
    }

    public void setPauseProductionTime(Date pauseProductionTime) {
        this.pauseProductionTime = pauseProductionTime;
    }

    public Date getEndProductionTime() {
        return endProductionTime;
    }

    public void setEndProductionTime(Date endProductionTime) {
        this.endProductionTime = endProductionTime;
    }

    public int getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(int corporationId) {
        this.corporationId = corporationId;
    }
}
