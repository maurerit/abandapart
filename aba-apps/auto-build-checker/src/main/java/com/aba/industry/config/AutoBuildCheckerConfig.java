/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.config;

import com.aba.eveonline.repo.ItemTypeRepository;
import com.aba.eveonline.repo.NullItemTypeRepository;
import com.aba.industry.fetch.client.BuildRequirementsProvider;
import com.aba.industry.fetch.client.CostIndexProvider;
import com.aba.industry.fetch.service.impl.FuzzySteveService;
import com.aba.industry.fetch.service.impl.StaticDataExportBlueprintYamlService;
import com.aba.industry.service.IndustryCalculationService;
import com.aba.industry.service.LocalIndustryCalculationService;
import com.aba.market.fetch.MarketOrderFetcher;
import com.aba.market.fetch.impl.CrestBulkMarketOrderFetcher;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Created by maurerit on 9/6/16.
 */
@Configuration
public class AutoBuildCheckerConfig {
    @Bean
    public IndustryCalculationService industryCalculationService ( ) {
        return new LocalIndustryCalculationService();
    }

    @Bean
    public BuildRequirementsProvider buildRequirementsProvider ( ) throws IOException {
        return new StaticDataExportBlueprintYamlService();
    }

    @Bean
    public CostIndexProvider costIndexProvider ( ) {
        return new FuzzySteveService();
    }

    @Bean
    public MarketOrderFetcher marketOrderFetcher ( ) {
        return new CrestBulkMarketOrderFetcher();
    }

    @Bean
    public ItemTypeRepository itemTypeRepository ( ) {
        return new NullItemTypeRepository();
    }

    @Bean
    public FanoutExchange exchange ( ) {
        return new FanoutExchange( "build-calc" );
    }
}
