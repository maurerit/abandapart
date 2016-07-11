package com.eveonline.api.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.eveonline.api.domain.Contracts;

public interface ContractsRepository  extends JpaRepository<Contracts, Long> {
}
