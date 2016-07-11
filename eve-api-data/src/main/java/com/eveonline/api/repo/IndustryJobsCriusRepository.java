package com.eveonline.api.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.eveonline.api.domain.IndustryJobCruis;

public interface IndustryJobsCriusRepository  extends JpaRepository<IndustryJobCruis, Long> {
}
