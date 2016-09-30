/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.impl;

import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.data.domain.config.InventionSkillConfiguration;
import com.aba.eveonline.repo.RegionRepository;
import com.aba.industry.AutoBuildChecker;
import com.aba.industry.fetch.client.BuildRequirementsProvider;
import com.aba.industry.model.BuildCalculationRequest;
import com.aba.industry.model.BuildCalculationResult;
import com.aba.industry.model.fuzzysteve.BlueprintData;
import com.aba.industry.service.IndustryCalculationService;
import com.aba.market.TradeHubs;
import com.aba.market.fetch.BulkMarketOrderFetcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by maurerit on 9/6/16.
 */
@Component
public class StreamBasedAutoBuildChecker implements AutoBuildChecker {
    private static final Logger       logger       = LoggerFactory.getLogger( StreamBasedAutoBuildChecker.class );
    private final        ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private ApplicationContext context;
    @Autowired
    private BuildRequirementsProvider buildRequirementsProvider;
    @Autowired
    private IndustryCalculationService industryCalculationService;
    @Autowired
    private BulkMarketOrderFetcher bulkMarketOrderFetcher;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private RabbitTemplate queueTemplate;

    @Override
    public void run ( ) {
        List<BlueprintData> bps = buildRequirementsProvider.getAllBlueprints();

        final IndustrySkillConfiguration industrySkills = new IndustrySkillConfiguration();
        industrySkills.setIndustrySkillLevel( 5 );
        industrySkills.setAdvancedIndustrySkillLevel( 5 );

        final InventionSkillConfiguration inventionSkills = new InventionSkillConfiguration();
        inventionSkills.setDatacoreOneSkillLevel( 3 );
        inventionSkills.setDatacoreTwoSkillLevel( 3 );
        inventionSkills.setEncryptionSkillLevel( 4 );

        logger.debug( "Seeding bulk market order cache" );
        Long regionId = regionRepository.findRegionId( TradeHubs.JITA.getRegionName() );
        bulkMarketOrderFetcher.getAllMarketOrders( regionId );
        logger.debug( "Finished seeding bulk market order cache" );

        Profiler profiler = new Profiler( "Build Calcs" );
        profiler.setLogger( logger );
        profiler.start( "CALC ALL THE THINGS" );
        bps.parallelStream()
           .map( bpData -> {
               logger.debug( "Requesting stuff for {}",
                             bpData.getRequestedId() );
               BuildCalculationRequest request = new BuildCalculationRequest();
               request.setRequestedBuildTypeId( bpData.getRequestedId() );
               request.setIndustrySkills( industrySkills );
               request.setInventionSkills( inventionSkills );
               request.setSystemName( "Atreen" );
               request.setMeLevel( 10 );
               request.setTeLevel( 20 );

               BuildCalculationResult result = null;

               try {
                   result = industryCalculationService.calculateBuild( request );
                   logger.debug( "Received stuff for {}",
                                 bpData.getRequestedId() );
                   queueTemplate.convertAndSend( "build-calc", "", objectMapper.writeValueAsString( result ) );
               }
               catch ( IOException e ) {
                   logger.error(
                           "Caught IOException while calculating request {}",
                           request );
               }

               return result;
           } )
           .collect( Collectors.toList() );
        profiler.stop();
        profiler.print();
    }
}
