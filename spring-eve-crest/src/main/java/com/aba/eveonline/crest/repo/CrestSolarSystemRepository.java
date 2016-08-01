/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the License.
 */

package com.aba.eveonline.crest.repo;

import com.aba.eveonline.repo.SolarSystemRepository;
import lombok.Setter;
import org.devfleet.crest.CrestService;
import org.devfleet.crest.model.CrestSolarSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.OptionalLong;

/**
 * Created by maurerit on 7/26/2016.
 */
@Repository
@Setter
public class CrestSolarSystemRepository implements SolarSystemRepository {
    @Autowired
    private CrestService crestService;


    @Override
    @Cacheable( "solar-system-id" )
    public Long getSolarSystemId ( String systemName )
    {
        Long result = null;

        List<CrestSolarSystem> solarSystems = crestService.getLocations();

        //TODO: Look into orElseGet
        OptionalLong systemId = solarSystems.stream()
                                            .filter( system -> system.getName()
                                                                     .equalsIgnoreCase( systemName ) )
                                            .mapToLong( system -> system.getId() )
                                            .findFirst();

        if ( systemId.isPresent() ) {
            result = systemId.getAsLong();
        }

        return result;
    }
}
