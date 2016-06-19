package com.eveonline.api.repo;


import com.eveonline.api.data.IndustryJobs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndustryJobsRepository  extends JpaRepository<IndustryJobs, Long> {
}
