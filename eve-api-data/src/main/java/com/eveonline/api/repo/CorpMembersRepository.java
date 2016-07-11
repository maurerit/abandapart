package com.eveonline.api.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.eveonline.api.domain.CorpMembers;

public interface CorpMembersRepository  extends JpaRepository<CorpMembers, Long> {
}
