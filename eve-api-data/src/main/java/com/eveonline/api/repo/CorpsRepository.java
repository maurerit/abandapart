package com.eveonline.api.repo;


import com.eveonline.api.data.Corps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorpsRepository  extends JpaRepository<Corps, Long> {
}
