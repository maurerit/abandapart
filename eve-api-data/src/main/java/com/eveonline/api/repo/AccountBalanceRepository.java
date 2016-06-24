package com.eveonline.api.repo;


import com.eveonline.api.data.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Long>
{
    AccountBalance findByAccountKeyAndCorporationId(int a, long c);
}
