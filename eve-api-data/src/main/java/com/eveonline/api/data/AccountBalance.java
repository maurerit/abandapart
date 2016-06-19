package com.eveonline.api.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="apiaccountbalance")
public class AccountBalance
{
    private int accountId;
    private int accountKey;
    private double balance;
    private int corporationId;

    @Id
    public int getAccountId() {
        return accountId;
    }
    public int getAccountKey() {
        return accountKey;
    }
    public double getBalance() {
        return balance;
    }
    public int getCorporationId() {
        return corporationId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public void setAccountKey(int accountKey) {
        this.accountKey = accountKey;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setCorporationId(int corporationId) {
        this.corporationId = corporationId;
    }
}
