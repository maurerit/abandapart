package com.eveonline.api.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.eveonline.api.domain.IndustryJob;

public interface IndustryJobsRepository  extends JpaRepository<IndustryJob, Long> {
}
