package com.aba.industry.data;

import java.util.Map;

import com.aba.data.TypedJsonMessage;

public class SampleDataType extends TypedJsonMessage {
	private String id;
	private int quantity;
	private double amount;
	private Map<String, String> keyValues;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Map<String, String> getKeyValues() {
		return keyValues;
	}

	public void setKeyValues(Map<String, String> keyValues) {
		this.keyValues = keyValues;
	}
}
