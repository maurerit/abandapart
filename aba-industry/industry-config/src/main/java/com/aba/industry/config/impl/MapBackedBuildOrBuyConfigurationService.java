/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.config.impl;

import com.aba.data.domain.config.BuildOrBuyConfiguration;
import com.aba.industry.config.BuildOrBuyConfigurationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A very simple implementation which matches the use case of being used inside the industry bus.  That use case is to
 * be told about the configuration each time it's going to be used and cleared after it's usage is done.  This is easy
 * since the bus has many workers and each client will be directly connected to one worker for a short period of time.
 * The worker is told about what is to be inserted into this and will then remove them all once its done for that
 * request.
 */
//TODO: For now have this auto discovered.  Later, provide an AutoConfiguration module which can be configured
@Service
public class MapBackedBuildOrBuyConfigurationService implements BuildOrBuyConfigurationService {
    private Map<Integer, BuildOrBuyConfiguration> configurations = new HashMap<>();

    @Override
    public List<BuildOrBuyConfiguration> getAllBuildOrBuyConfigurations ( ) {
        List<BuildOrBuyConfiguration> result = null;

        result = configurations.entrySet()
                               .stream()
                               .map( Map.Entry::getValue )
                               .collect( Collectors.toList() );

        return result;
    }

    @Override
    public BuildOrBuyConfiguration findByTypeId ( Integer typeId ) {
        BuildOrBuyConfiguration result = new BuildOrBuyConfiguration( typeId, BuildOrBuyConfiguration.BuildOrBuy.BUY );

        if ( configurations.containsKey( typeId ) ) {
            result = configurations.get( typeId );
        }
        else if ( configurations.containsKey( -1 ) ) {
            result = configurations.get( -1 );
        }

        return result;
    }

    @Override
    public void createOrUpdateBuildOrBuyConfiguration ( BuildOrBuyConfiguration config ) {
        configurations.put( config.getTypeId(), config );
    }

    @Override
    public void deleteBuildOrByConfiguration ( BuildOrBuyConfiguration config ) {
        configurations.remove( config.getTypeId() );
    }
}
