/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.router.client.impl;

import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.data.domain.config.InventionSkillConfiguration;
import com.aba.industry.model.BuildCalculationRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

/**
 * Created by maurerit on 8/8/16.
 */
@SpringBootApplication
@EnableAutoConfiguration( exclude = { HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class } )
public class LocalRouterCommunicationApp {
    public static void main ( String[] args ) throws IOException {
        ApplicationContext context = SpringApplication.run( LocalRouterCommunicationApp.class, args );
        IndustrialCalculatorRouterClientImpl client = context.getBean( IndustrialCalculatorRouterClientImpl.class );

        BuildCalculationRequest request = new BuildCalculationRequest();

        IndustrySkillConfiguration industrySkills = new IndustrySkillConfiguration();
        industrySkills.setAdvancedIndustrySkillLevel( 5 );
        industrySkills.setIndustrySkillLevel( 5 );

        InventionSkillConfiguration inventionSkills = new InventionSkillConfiguration();
        inventionSkills.setDatacoreOneSkillLevel( 3 );
        inventionSkills.setDatacoreTwoSkillLevel( 3 );
        inventionSkills.setEncryptionSkillLevel( 4 );

        request.setIndustrySkills( industrySkills );
        request.setInventionSkills( inventionSkills );
        request.setMeLevel( 10 );
        request.setTeLevel( 20 );
        request.setRequestedBuildTypeId( 22444 );

        request.setSystemName( "Atreen" );

        client.calculateBuild( request );
    }
}
