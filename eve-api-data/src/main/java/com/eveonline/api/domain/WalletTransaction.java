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
@Table( name = "apiwallettransactions" )
public class WalletTransaction {
    private Date       transactionDateTime;
    @Id
    private long       transactionId;
    private long       quantity;
    private String     typeName;
    private int        typeId;
    private BigDecimal price;
    private long       clientId;
    private String     clientName;
    private int        stationId;
    private String     stationName;
    private String     transactionType;
    private String     transactionFor;
    private long       journalTransactionId;
    private int        accountKey;
    private long       corporationId;

    public Date getTransactionDateTime ( ) {
        return transactionDateTime;
    }

    public void setTransactionDateTime ( Date transactionDateTime ) {
        this.transactionDateTime = transactionDateTime;
    }

    public long getTransactionId ( ) {
        return transactionId;
    }

    public void setTransactionId ( long transactionId ) {
        this.transactionId = transactionId;
    }

    public long getQuantity ( ) {
        return quantity;
    }

    public void setQuantity ( long quantity ) {
        this.quantity = quantity;
    }

    public String getTypeName ( ) {
        return typeName;
    }

    public void setTypeName ( String typeName ) {
        this.typeName = typeName;
    }

    public int getTypeId ( ) {
        return typeId;
    }

    public void setTypeId ( int typeId ) {
        this.typeId = typeId;
    }

    public BigDecimal getPrice ( ) {
        return price;
    }

    public void setPrice ( BigDecimal price ) {
        this.price = price;
    }

    public long getClientId ( ) {
        return clientId;
    }

    public void setClientId ( long clientId ) {
        this.clientId = clientId;
    }

    public String getClientName ( ) {
        return clientName;
    }

    public void setClientName ( String clientName ) {
        this.clientName = clientName;
    }

    public int getStationId ( ) {
        return stationId;
    }

    public void setStationId ( int stationId ) {
        this.stationId = stationId;
    }

    public String getStationName ( ) {
        return stationName;
    }

    public void setStationName ( String stationName ) {
        this.stationName = stationName;
    }

    public String getTransactionType ( ) {
        return transactionType;
    }

    public void setTransactionType ( String transactionType ) {
        this.transactionType = transactionType;
    }

    public String getTransactionFor ( ) {
        return transactionFor;
    }

    public void setTransactionFor ( String transactionFor ) {
        this.transactionFor = transactionFor;
    }

    public long getJournalTransactionId ( ) {
        return journalTransactionId;
    }

    public void setJournalTransactionId ( long journalTransactionId ) {
        this.journalTransactionId = journalTransactionId;
    }

    public int getAccountKey ( ) {
        return accountKey;
    }

    public void setAccountKey ( int accountKey ) {
        this.accountKey = accountKey;
    }

    public long getCorporationId ( ) {
        return corporationId;
    }

    public void setCorporationId ( long corporationId ) {
        this.corporationId = corporationId;
    }
}
