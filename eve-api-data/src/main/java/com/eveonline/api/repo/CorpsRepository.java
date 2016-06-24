package com.eveonline.api.repo;


import com.eveonline.api.data.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorpsRepository  extends JpaRepository<Corporation, Long> {
}
