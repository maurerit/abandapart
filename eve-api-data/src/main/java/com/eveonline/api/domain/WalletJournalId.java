package com.eveonline.api.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class WalletJournalId implements Serializable {
	private static final long serialVersionUID = -6468799821324480913L;
	private Date entryDate;
	private long refId;
}
