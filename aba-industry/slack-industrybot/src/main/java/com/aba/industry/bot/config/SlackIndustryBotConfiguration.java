/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.bot.config;

import com.aba.industry.fetch.service.impl.FuzzySteveService;
import com.aba.industry.router.client.impl.IndustrialCalculatorRouterClientImpl;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.util.concurrent.Executor;

/**
 * Created by maurerit on 8/7/16.
 */
@Configuration
@ConfigurationProperties( prefix = "aba.industry.bot" )
public class SlackIndustryBotConfiguration {
    private static final Logger logger = LoggerFactory.getLogger( SlackIndustryBotConfiguration.class );

    @Value( "${aba.industry.bot.authToken}" )
    private String authToken;

    @Bean
    public SlackSession slackSession ( ) {
        SlackSession session = SlackSessionFactory.createWebSocketSlackSession( authToken );
        try {
            session.connect();
        }
        catch ( IOException e ) {
            logger.error( "Error connecting to slack server.", e );
            throw new RuntimeException( e );
        }
        return session;
    }

    @Bean
    public FuzzySteveService fuzzySteveService ( ) {
        return new FuzzySteveService();
    }

    @Bean( name = "threadPoolTaskExecutor" )
    public Executor threadPoolTaskExecutor ( ) {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize( 2 );
        pool.setMaxPoolSize( 20 );
        pool.setQueueCapacity( 2 );
        return pool;
    }

    @Bean
    @Scope( scopeName = "prototype" )
    public IndustrialCalculatorRouterClientImpl routerClient ( ) {
        return new IndustrialCalculatorRouterClientImpl();
    }

//    @Bean
//    public BuildCalculationRequestResponder buildCalculationRequestResponder ( ) {
//        return new BuildCalculationRequestResponder();
//    }
}
