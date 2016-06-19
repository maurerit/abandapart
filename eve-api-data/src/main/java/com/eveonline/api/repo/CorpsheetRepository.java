package com.eveonline.api.repo;


import com.eveonline.api.data.Corpsheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorpsheetRepository  extends JpaRepository<Corpsheet, Long> {
}
