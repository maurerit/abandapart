package com.eveonline.api.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.eveonline.api.domain.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
