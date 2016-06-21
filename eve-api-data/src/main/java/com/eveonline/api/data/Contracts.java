package com.eveonline.api.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "apicontracts")
public class Contracts
{
    private long contractId;
    private long issuerId;
    private long issuerCorpId;
    private long assigneeId;
    private long acceptorId;
    private long startStationId;
    private long endStationId;
    private String type;
    private String status;
    private String title;
    private int forCorp;
    private String availability;
    private Date dateIssued;
    private Date dateExpired;
    private Date dateAccepted;
    private int numDays;
    private Date dateCompleted;
    private BigDecimal price;
    private BigDecimal reward;
    private BigDecimal collateral;
    private BigDecimal buyout;
    private BigDecimal volume;
    private long corporationId;

    @Id
    public long getContractId() {
        return contractId;
    }
    public long getIssuerId() {
        return issuerId;
    }
    public long getIssuerCorpId() {
        return issuerCorpId;
    }
    public long getAssigneeId() {
        return assigneeId;
    }
    public long getAcceptorId() {
        return acceptorId;
    }
    public long getStartStationId() {
        return startStationId;
    }
    public long getEndStationId() {
        return endStationId;
    }
    public String getType() {
        return type;
    }
    public String getStatus() {
        return status;
    }
    public String getTitle() {
        return title;
    }
    public int getForCorp() {
        return forCorp;
    }
    public String getAvailability() {
        return availability;
    }
    public Date getDateIssued() {
        return dateIssued;
    }
    public Date getDateExpired() {
        return dateExpired;
    }
    public Date getDateAccepted() {
        return dateAccepted;
    }
    public int getNumDays() {
        return numDays;
    }
    public Date getDateCompleted() {
        return dateCompleted;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public BigDecimal getReward() {
        return reward;
    }
    public BigDecimal getCollateral() {
        return collateral;
    }
    public BigDecimal getBuyout() {
        return buyout;
    }
    public BigDecimal getVolume() {
        return volume;
    }
    public long getCorporationId() {
        return corporationId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }
    public void setIssuerId(long issuerId) {
        this.issuerId = issuerId;
    }
    public void setIssuerCorpId(long issuerCorpId) {
        this.issuerCorpId = issuerCorpId;
    }
    public void setAssigneeId(long assigneeId) {
        this.assigneeId = assigneeId;
    }
    public void setAcceptorId(long acceptorId) {
        this.acceptorId = acceptorId;
    }
    public void setStartStationId(long startStationId) {
        this.startStationId = startStationId;
    }
    public void setEndStationId(long endStationId) {
        this.endStationId = endStationId;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setForCorp(int forCorp) {
        this.forCorp = forCorp;
    }
    public void setAvailability(String availability) {
        this.availability = availability;
    }
    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
    }
    public void setDateExpired(Date dateExpired) {
        this.dateExpired = dateExpired;
    }
    public void setDateAccepted(Date dateAccepted) {
        this.dateAccepted = dateAccepted;
    }
    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }
    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }
    public void setCollateral(BigDecimal collateral) {
        this.collateral = collateral;
    }
    public void setBuyout(BigDecimal buyout) {
        this.buyout = buyout;
    }
    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }
    public void setCorporationId(long corporationId) {
        this.corporationId = corporationId;
    }
}
