/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

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
public class CrestSolarSystemRepository implements SolarSystemRepository {
    @Autowired
    private CrestService crestService;


    @Override
    public long getSolarSystemId ( String systemName ) {
        long result = 0l;

        List<CrestSolarSystem> solarSystems = crestService.getLocations();

        result = solarSystems.stream()
                             .filter( system -> system.getName()
                                                      .equalsIgnoreCase( systemName ) )
                             .mapToLong( system -> system.getId() )
                             .findFirst()
                             .getAsLong();

        return result;
    }
}
