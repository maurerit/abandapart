package com.eveonline.api.data;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "apiwallettransactions")
public class WalletTransaction {
	private Date transactionDateTime;
	private long transactionId;
	private long quantity;
	private String typeName;
	private int typeId;
	private BigDecimal price;
	private long clientId;
	private String clientName;
	private int stationId;
	private String stationName;
	private String transactionType;
	private String transactionFor;
	private long journalTransactionId;
	private int accountKey;
	private long corporationId;

	public Date getTransactionDateTime() {
		return transactionDateTime;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public long getQuantity() {
		return quantity;
	}

	public String getTypeName() {
		return typeName;
	}

	public int getTypeId() {
		return typeId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public long getClientId() {
		return clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public int getStationId() {
		return stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public String getTransactionFor() {
		return transactionFor;
	}

	public long getJournalTransactionId() {
		return journalTransactionId;
	}

	public int getAccountKey() {
		return accountKey;
	}

	public long getCorporationId() {
		return corporationId;
	}

	public void setTransactionDateTime(Date transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public void setTransactionFor(String transactionFor) {
		this.transactionFor = transactionFor;
	}

	public void setJournalTransactionId(long journalTransactionId) {
		this.journalTransactionId = journalTransactionId;
	}

	public void setAccountKey(int accountKey) {
		this.accountKey = accountKey;
	}

	public void setCorporationId(long corporationId) {
		this.corporationId = corporationId;
	}
}
