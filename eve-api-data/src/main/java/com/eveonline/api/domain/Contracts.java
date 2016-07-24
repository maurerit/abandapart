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
@Table( name = "apicontracts" )
public class Contracts {
    private long       contractId;
    private long       issuerId;
    private long       issuerCorpId;
    private long       assigneeId;
    private long       acceptorId;
    private long       startStationId;
    private long       endStationId;
    private String     type;
    private String     status;
    private String     title;
    private int        forCorp;
    private String     availability;
    private Date       dateIssued;
    private Date       dateExpired;
    private Date       dateAccepted;
    private int        numDays;
    private Date       dateCompleted;
    private BigDecimal price;
    private BigDecimal reward;
    private BigDecimal collateral;
    private BigDecimal buyout;
    private BigDecimal volume;
    private long       corporationId;

    @Id
    public long getContractId ( ) {
        return contractId;
    }

    public void setContractId ( long contractId ) {
        this.contractId = contractId;
    }

    public long getIssuerId ( ) {
        return issuerId;
    }

    public void setIssuerId ( long issuerId ) {
        this.issuerId = issuerId;
    }

    public long getIssuerCorpId ( ) {
        return issuerCorpId;
    }

    public void setIssuerCorpId ( long issuerCorpId ) {
        this.issuerCorpId = issuerCorpId;
    }

    public long getAssigneeId ( ) {
        return assigneeId;
    }

    public void setAssigneeId ( long assigneeId ) {
        this.assigneeId = assigneeId;
    }

    public long getAcceptorId ( ) {
        return acceptorId;
    }

    public void setAcceptorId ( long acceptorId ) {
        this.acceptorId = acceptorId;
    }

    public long getStartStationId ( ) {
        return startStationId;
    }

    public void setStartStationId ( long startStationId ) {
        this.startStationId = startStationId;
    }

    public long getEndStationId ( ) {
        return endStationId;
    }

    public void setEndStationId ( long endStationId ) {
        this.endStationId = endStationId;
    }

    public String getType ( ) {
        return type;
    }

    public void setType ( String type ) {
        this.type = type;
    }

    public String getStatus ( ) {
        return status;
    }

    public void setStatus ( String status ) {
        this.status = status;
    }

    public String getTitle ( ) {
        return title;
    }

    public void setTitle ( String title ) {
        this.title = title;
    }

    public int getForCorp ( ) {
        return forCorp;
    }

    public void setForCorp ( int forCorp ) {
        this.forCorp = forCorp;
    }

    public String getAvailability ( ) {
        return availability;
    }

    public void setAvailability ( String availability ) {
        this.availability = availability;
    }

    public Date getDateIssued ( ) {
        return dateIssued;
    }

    public void setDateIssued ( Date dateIssued ) {
        this.dateIssued = dateIssued;
    }

    public Date getDateExpired ( ) {
        return dateExpired;
    }

    public void setDateExpired ( Date dateExpired ) {
        this.dateExpired = dateExpired;
    }

    public Date getDateAccepted ( ) {
        return dateAccepted;
    }

    public void setDateAccepted ( Date dateAccepted ) {
        this.dateAccepted = dateAccepted;
    }

    public int getNumDays ( ) {
        return numDays;
    }

    public void setNumDays ( int numDays ) {
        this.numDays = numDays;
    }

    public Date getDateCompleted ( ) {
        return dateCompleted;
    }

    public void setDateCompleted ( Date dateCompleted ) {
        this.dateCompleted = dateCompleted;
    }

    public BigDecimal getPrice ( ) {
        return price;
    }

    public void setPrice ( BigDecimal price ) {
        this.price = price;
    }

    public BigDecimal getReward ( ) {
        return reward;
    }

    public void setReward ( BigDecimal reward ) {
        this.reward = reward;
    }

    public BigDecimal getCollateral ( ) {
        return collateral;
    }

    public void setCollateral ( BigDecimal collateral ) {
        this.collateral = collateral;
    }

    public BigDecimal getBuyout ( ) {
        return buyout;
    }

    public void setBuyout ( BigDecimal buyout ) {
        this.buyout = buyout;
    }

    public BigDecimal getVolume ( ) {
        return volume;
    }

    public void setVolume ( BigDecimal volume ) {
        this.volume = volume;
    }

    public long getCorporationId ( ) {
        return corporationId;
    }

    public void setCorporationId ( long corporationId ) {
        this.corporationId = corporationId;
    }
}
