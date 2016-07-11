package com.eveonline.api.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.eveonline.api.domain.Corpsheet;

public interface CorpsheetRepository  extends JpaRepository<Corpsheet, Long> {
}
