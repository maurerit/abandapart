package com.eveonline.api.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "apicontracts")
public class Contracts
{
    private int contractId;
    private int issuerId;
    private int issuerCorpId;
    private int assigneeId;
    private int acceptorId;
    private int startStationId;
    private int endStationId;
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
    private double price;
    private double reward;
    private double collateral;
    private double buyout;
    private double volume;
    private int corporationId;

    @Id
    public int getContractId() {
        return contractId;
    }
    public int getIssuerId() {
        return issuerId;
    }
    public int getIssuerCorpId() {
        return issuerCorpId;
    }
    public int getAssigneeId() {
        return assigneeId;
    }
    public int getAcceptorId() {
        return acceptorId;
    }
    public int getStartStationId() {
        return startStationId;
    }
    public int getEndStationId() {
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
    public double getPrice() {
        return price;
    }
    public double getReward() {
        return reward;
    }
    public double getCollateral() {
        return collateral;
    }
    public double getBuyout() {
        return buyout;
    }
    public double getVolume() {
        return volume;
    }
    public int getCorporationId() {
        return corporationId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }
    public void setIssuerId(int issuerId) {
        this.issuerId = issuerId;
    }
    public void setIssuerCorpId(int issuerCorpId) {
        this.issuerCorpId = issuerCorpId;
    }
    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }
    public void setAcceptorId(int acceptorId) {
        this.acceptorId = acceptorId;
    }
    public void setStartStationId(int startStationId) {
        this.startStationId = startStationId;
    }
    public void setEndStationId(int endStationId) {
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
    public void setPrice(double price) {
        this.price = price;
    }
    public void setReward(double reward) {
        this.reward = reward;
    }
    public void setCollateral(double collateral) {
        this.collateral = collateral;
    }
    public void setBuyout(double buyout) {
        this.buyout = buyout;
    }
    public void setVolume(double volume) {
        this.volume = volume;
    }
    public void setCorporationId(int corporationId) {
        this.corporationId = corporationId;
    }
}
