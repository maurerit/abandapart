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

@Entity
@Table( name = "apiaccountbalance" )
public class AccountBalance {
    private long       accountId;
    private int        accountKey;
    private BigDecimal balance;
    private long       corporationId;

    @Id
    public long getAccountId ( ) {
        return accountId;
    }

    public void setAccountId ( long accountId ) {
        this.accountId = accountId;
    }

    public int getAccountKey ( ) {
        return accountKey;
    }

    public void setAccountKey ( int accountKey ) {
        this.accountKey = accountKey;
    }

    public BigDecimal getBalance ( ) {
        return balance;
    }

    public void setBalance ( BigDecimal balance ) {
        this.balance = balance;
    }

    public long getCorporationId ( ) {
        return corporationId;
    }

    public void setCorporationId ( long corporationId ) {
        this.corporationId = corporationId;
    }
}
