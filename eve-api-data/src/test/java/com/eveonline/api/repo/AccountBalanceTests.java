package com.eveonline.api.repo;

import com.eveonline.api.BaseApiTestCase;
import com.eveonline.api.data.AccountBalance;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

public class AccountBalanceTests extends BaseApiTestCase {

	@Autowired
	private AccountBalanceRepository repo; //Test
	
	@Test
	@Sql({"/testGetAccountId34339379Balance.sql"})
	public void testGetAccountId34339379Balance() {
		AccountBalance balance = repo.findByAccountKeyAndCorporationId(1000, 1l);
		BigDecimal expected = new BigDecimal(200000.35);
		expected = expected.setScale(2, BigDecimal.ROUND_HALF_UP);
		assertEquals("Incorrect balance for corp and account key", expected, balance.getBalance());
	}
}