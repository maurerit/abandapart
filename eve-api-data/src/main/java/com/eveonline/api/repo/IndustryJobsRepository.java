package com.eveonline.api.repo;


import com.eveonline.api.data.IndustryJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndustryJobsRepository  extends JpaRepository<IndustryJob, Long> {
}
