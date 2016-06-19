package com.eveonline.api.repo;


import com.eveonline.api.data.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Long> {
}
