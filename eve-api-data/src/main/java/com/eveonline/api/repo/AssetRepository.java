package com.eveonline.api.repo;


import com.eveonline.api.data.Assets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Assets, Long> {
}
