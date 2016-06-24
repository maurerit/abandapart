package com.eveonline.api.repo;


import com.eveonline.api.data.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
