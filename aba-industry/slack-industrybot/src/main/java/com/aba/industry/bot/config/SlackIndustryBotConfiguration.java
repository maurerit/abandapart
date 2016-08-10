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

import com.aba.industry.bot.async.SlackPostingAsyncUncaughtExceptionHandler;
import com.aba.industry.fetch.service.impl.FuzzySteveService;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;

import java.io.IOException;

/**
 * Created by maurerit on 8/7/16.
 */
@Configuration
@ConfigurationProperties( prefix = "aba.industry.bot.authToken" )
public class SlackIndustryBotConfiguration extends AsyncConfigurerSupport {
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

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler ( ) {
        return new SlackPostingAsyncUncaughtExceptionHandler();
    }

    //    @Bean
//    public TypeIdNotFoundResponder typeIdNotFoundResponder ( ) {
//        return new TypeIdNotFoundResponder();
//    }
//
//    @Bean
//    public ExceptionErrorResponder exceptionErrorResponder ( ) {
//        return new ExceptionErrorResponder();
//    }
}
