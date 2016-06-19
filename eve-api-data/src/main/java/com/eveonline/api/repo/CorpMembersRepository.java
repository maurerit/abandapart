package com.eveonline.api.repo;


import com.eveonline.api.data.CorpMembers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorpMembersRepository  extends JpaRepository<CorpMembers, Long> {
}
