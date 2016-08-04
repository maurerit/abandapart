/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.eveonline.crest.repo;

import com.aba.eveonline.repo.RegionRepository;
import lombok.Setter;
import org.devfleet.crest.CrestService;
import org.devfleet.crest.model.CrestItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.OptionalLong;

/**
 * Created by maurerit on 7/25/16.
 */
@Repository
@Setter
public class CrestRegionRepository implements RegionRepository {
    @Autowired
    private CrestService crestService;

    @Override
    @Cacheable( "crest-regions" )
    public List<CrestItem> getRegions ( ) {
        return crestService.getRegions();
    }

    @Override
    @Cacheable( "crest-region-id" )
    public Long findRegionId ( String regionName )
    {
        Long result = null;

        //TODO: Look into orElseGet
        OptionalLong regionId = getRegions().stream()
                                            .filter( region -> region.getName()
                                                                     .equalsIgnoreCase( regionName ) )
                                            .mapToLong( region -> region.getId() )
                                            .findFirst();

        if ( regionId.isPresent() ) {
            result = regionId.getAsLong();
        }

        return result;
    }
}
