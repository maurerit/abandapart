package com.eveonline.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eveonline.api.data.AccountBalance;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Long>
{
    AccountBalance findByAccountKeyAndCorporationId(int a, long c);
}
