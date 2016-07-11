package com.eveonline.api.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="apiaccountbalance")
public class AccountBalance
{
    private long accountId;
    private int accountKey;
    private BigDecimal balance;
    private long corporationId;

    @Id
    public long getAccountId() {
        return accountId;
    }
    public int getAccountKey() {
        return accountKey;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public long getCorporationId() {
        return corporationId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
    public void setAccountKey(int accountKey) {
        this.accountKey = accountKey;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public void setCorporationId(long corporationId) {
        this.corporationId = corporationId;
    }
}
