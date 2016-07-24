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

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table( name = "apiwalletjournal" )
@IdClass( WalletJournalId.class )
public class WalletJournal {
    @Column( name = "date" )
    @Id
    private Date       entryDate;
    @Id
    private long       refId;
    private int        refTypeId;
    private String     ownerNameOne;
    private int        ownerIdOne;
    private String     ownerNameTwo;
    private int        ownerIdTwo;
    private String     argNameOne;
    private int        argIdOne;
    private BigDecimal amount;
    private BigDecimal balance;
    private String     reason;
    private int        accountKey;
    private long       corporationId;

    public Date getEntryDate ( ) {
        return entryDate;
    }

    public void setEntryDate ( Date entryDate ) {
        this.entryDate = entryDate;
    }

    public long getRefId ( ) {
        return refId;
    }

    public void setRefId ( long refId ) {
        this.refId = refId;
    }

    public int getRefTypeId ( ) {
        return refTypeId;
    }

    public void setRefTypeId ( int refTypeId ) {
        this.refTypeId = refTypeId;
    }

    public String getOwnerNameOne ( ) {
        return ownerNameOne;
    }

    public void setOwnerNameOne ( String ownerNameOne ) {
        this.ownerNameOne = ownerNameOne;
    }

    public int getOwnerIdOne ( ) {
        return ownerIdOne;
    }

    public void setOwnerIdOne ( int ownerIdOne ) {
        this.ownerIdOne = ownerIdOne;
    }

    public String getOwnerNameTwo ( ) {
        return ownerNameTwo;
    }

    public void setOwnerNameTwo ( String ownerNameTwo ) {
        this.ownerNameTwo = ownerNameTwo;
    }

    public int getOwnerIdTwo ( ) {
        return ownerIdTwo;
    }

    public void setOwnerIdTwo ( int ownerIdTwo ) {
        this.ownerIdTwo = ownerIdTwo;
    }

    public String getArgNameOne ( ) {
        return argNameOne;
    }

    public void setArgNameOne ( String argNameOne ) {
        this.argNameOne = argNameOne;
    }

    public int getArgIdOne ( ) {
        return argIdOne;
    }

    public void setArgIdOne ( int argIdOne ) {
        this.argIdOne = argIdOne;
    }

    public BigDecimal getAmount ( ) {
        return amount;
    }

    public void setAmount ( BigDecimal amount ) {
        this.amount = amount;
    }

    public BigDecimal getBalance ( ) {
        return balance;
    }

    public void setBalance ( BigDecimal balance ) {
        this.balance = balance;
    }

    public String getReason ( ) {
        return reason;
    }

    public void setReason ( String reason ) {
        this.reason = reason;
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
