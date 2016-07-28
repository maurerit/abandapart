/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the License.
 */

package com.eveonline.api.repo;

import com.eveonline.api.BaseApiTestCase;
import com.eveonline.api.domain.AccountBalance;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AccountBalanceTests extends BaseApiTestCase {

    @Autowired
    private AccountBalanceRepository repo; //Test

    @Test
    @Sql( { "/testGetAccountKey1000Balance.sql" } )
    public void testGetAccountKey1000Balance ( ) {
        AccountBalance balance = repo.findByAccountKeyAndCorporationId( 1000, 1l );
        BigDecimal expected = new BigDecimal( 200000.35 );
        expected = expected.setScale( 2, BigDecimal.ROUND_HALF_UP );
        assertEquals( "Incorrect balance for corp and account key", expected, balance.getBalance() );
    }
}