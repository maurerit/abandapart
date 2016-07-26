package com.aba.eveonline.crest.repo.impl;

import com.aba.eveonline.crest.repo.SolarSystemRepository;
import lombok.Getter;
import org.devfleet.crest.CrestService;
import org.devfleet.crest.model.CrestSolarSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mm66053 on 7/26/2016.
 */
@Repository
@Getter
public class CrestSolarSystemRepository implements SolarSystemRepository{
    @Autowired
    private CrestService crestService;


    @Override
    public long getSolarSystemId(String systemName) {
        long result = 0l;

        List<CrestSolarSystem> solarSystems = crestService.getLocations();

        result = solarSystems.stream()
                .filter( system -> system.getName().equalsIgnoreCase( systemName ) )
                .mapToLong( system -> system.getId() )
                .findFirst()
                .getAsLong();

        return result;
    }
}
