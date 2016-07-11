package com.eveonline.api.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.eveonline.api.domain.ContractItems;

public interface ContractItemsRepository  extends JpaRepository<ContractItems, Long> {
}
