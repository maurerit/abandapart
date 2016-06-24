package com.eveonline.api.data;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "apiwalletjournal")
public class WalletJournal {
	@Column(name = "date")
	private Date entryDate;
	private long refId;
	private int refTypeId;
	private String ownerNameOne;
	private int ownerIdOne;
	private String ownerNameTwo;
	private int ownerIdTwo;
	private String argNameOne;
	private int argIdOne;
	private BigDecimal amount;
	private BigDecimal balance;
	private String reason;
	private int accountKey;
	private long corporationId;

	public Date getEntryDate() {
		return entryDate;
	}

	public long getRefId() {
		return refId;
	}

	public int getRefTypeId() {
		return refTypeId;
	}

	public String getOwnerNameOne() {
		return ownerNameOne;
	}

	public int getOwnerIdOne() {
		return ownerIdOne;
	}

	public String getOwnerNameTwo() {
		return ownerNameTwo;
	}

	public int getOwnerIdTwo() {
		return ownerIdTwo;
	}

	public String getArgNameOne() {
		return argNameOne;
	}

	public int getArgIdOne() {
		return argIdOne;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public String getReason() {
		return reason;
	}

	public int getAccountKey() {
		return accountKey;
	}

	public long getCorporationId() {
		return corporationId;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public void setRefId(long refId) {
		this.refId = refId;
	}

	public void setRefTypeId(int refTypeId) {
		this.refTypeId = refTypeId;
	}

	public void setOwnerNameOne(String ownerNameOne) {
		this.ownerNameOne = ownerNameOne;
	}

	public void setOwnerIdOne(int ownerIdOne) {
		this.ownerIdOne = ownerIdOne;
	}

	public void setOwnerNameTwo(String ownerNameTwo) {
		this.ownerNameTwo = ownerNameTwo;
	}

	public void setOwnerIdTwo(int ownerIdTwo) {
		this.ownerIdTwo = ownerIdTwo;
	}

	public void setArgNameOne(String argNameOne) {
		this.argNameOne = argNameOne;
	}

	public void setArgIdOne(int argIdOne) {
		this.argIdOne = argIdOne;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setAccountKey(int accountKey) {
		this.accountKey = accountKey;
	}

	public void setCorporationId(long corporationId) {
		this.corporationId = corporationId;
	}
}
