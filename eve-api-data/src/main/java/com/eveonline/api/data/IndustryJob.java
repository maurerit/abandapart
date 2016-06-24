package com.eveonline.api.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "apiindustryjobs")
public class IndustryJob
{
    private long jobId;
    private long assemblyLineId;
    private long containerId;
    private long installedItemId;
    private long installedItemLocationId;
    private long installedItemQuantity;
    private long installedItemProductivityLevel;
    private long installedItemMaterialLevel;
    private long installedItemLicensedProductionRunsRemaining;
    private long outputLocationId;
    private long installerId;
    private int runs;
    private int licensedProductionRuns;
    private long installedInSolarSystemId;
    private long containerLocationId;
    private BigDecimal materialMultiplier;
    private BigDecimal charMaterialMultiplier;
    private BigDecimal timeMultiplier;
    private BigDecimal charTimeMultiplier;
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
    private long corporationId;

    @Id
    public long getJobId() {
        return jobId;
    }
    public long getAssemblyLineId() {
        return assemblyLineId;
    }
    public long getContainerId() {
        return containerId;
    }
    public long getInstalledItemId() {
        return installedItemId;
    }
    public long getInstalledItemLocationId() {
        return installedItemLocationId;
    }
    public long getInstalledItemQuantity() {
        return installedItemQuantity;
    }
    public long getInstalledItemProductivityLevel() {
        return installedItemProductivityLevel;
    }
    public long getInstalledItemMaterialLevel() {
        return installedItemMaterialLevel;
    }
    public long getInstalledItemLicensedProductionRunsRemaining() {
        return installedItemLicensedProductionRunsRemaining;
    }
    public long getOutputLocationId() {
        return outputLocationId;
    }
    public long getInstallerId() {
        return installerId;
    }
    public int getRuns() {
        return runs;
    }
    public int getLicensedProductionRuns() {
        return licensedProductionRuns;
    }
    public long getInstalledInSolarSystemId() {
        return installedInSolarSystemId;
    }
    public long getContainerLocationId() {
        return containerLocationId;
    }
    public BigDecimal getMaterialMultiplier() {
        return materialMultiplier;
    }
    public BigDecimal getCharMaterialMultiplier() {
        return charMaterialMultiplier;
    }
    public BigDecimal getTimeMultiplier() {
        return timeMultiplier;
    }
    public BigDecimal getCharTimeMultiplier() {
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
    public long getCorporationId() {
        return corporationId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }
    public void setAssemblyLineId(long assemblyLineId) {
        this.assemblyLineId = assemblyLineId;
    }
    public void setContainerId(long containerId) {
        this.containerId = containerId;
    }
    public void setInstalledItemId(long installedItemId) {
        this.installedItemId = installedItemId;
    }
    public void setInstalledItemLocationId(long installedItemLocationId) {
        this.installedItemLocationId = installedItemLocationId;
    }
    public void setInstalledItemQuantity(long installedItemQuantity) {
        this.installedItemQuantity = installedItemQuantity;
    }
    public void setInstalledItemProductivityLevel(long installedItemProductivityLevel) {
        this.installedItemProductivityLevel = installedItemProductivityLevel;
    }
    public void setInstalledItemMaterialLevel(long installedItemMaterialLevel) {
        this.installedItemMaterialLevel = installedItemMaterialLevel;
    }
    public void setInstalledItemLicensedProductionRunsRemaining(long installedItemLicensedProductionRunsRemaining) {
        this.installedItemLicensedProductionRunsRemaining = installedItemLicensedProductionRunsRemaining;
    }
    public void setOutputLocationId(long outputLocationId) {
        this.outputLocationId = outputLocationId;
    }
    public void setInstallerId(long installerId) {
        this.installerId = installerId;
    }
    public void setRuns(int runs) {
        this.runs = runs;
    }
    public void setLicensedProductionRuns(int licensedProductionRuns) {
        this.licensedProductionRuns = licensedProductionRuns;
    }
    public void setInstalledInSolarSystemId(long installedInSolarSystemId) {
        this.installedInSolarSystemId = installedInSolarSystemId;
    }
    public void setContainerLocationId(long containerLocationId) {
        this.containerLocationId = containerLocationId;
    }
    public void setMaterialMultiplier(BigDecimal materialMultiplier) {
        this.materialMultiplier = materialMultiplier;
    }
    public void setCharMaterialMultiplier(BigDecimal charMaterialMultiplier) {
        this.charMaterialMultiplier = charMaterialMultiplier;
    }
    public void setTimeMultiplier(BigDecimal timeMultiplier) {
        this.timeMultiplier = timeMultiplier;
    }
    public void setCharTimeMultiplier(BigDecimal charTimeMultiplier) {
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
    public void setCorporationId(long corporationId) {
        this.corporationId = corporationId;
    }
}
