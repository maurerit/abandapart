package com.eveonline.api.repo;


import com.eveonline.api.data.Contracts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractsRepository  extends JpaRepository<Contracts, Long> {
}
