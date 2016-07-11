package com.eveonline.api.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.eveonline.api.domain.Corporation;

public interface CorpsRepository  extends JpaRepository<Corporation, Long> {
}
