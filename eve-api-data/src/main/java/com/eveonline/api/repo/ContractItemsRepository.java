package com.eveonline.api.repo;


import com.eveonline.api.data.ContractItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractItemsRepository  extends JpaRepository<ContractItems, Long> {
}
