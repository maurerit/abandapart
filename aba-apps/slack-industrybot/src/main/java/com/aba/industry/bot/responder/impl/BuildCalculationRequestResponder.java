/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.bot.responder.impl;

import com.aba.industry.bot.async.AsyncSlackException;
import com.aba.industry.bot.responder.RequestResponder;
import com.aba.industry.bot.util.MessageUtils;
import com.aba.industry.bus.model.BuildCalculationRequest;
import com.aba.industry.model.BuildCalculationResult;
import com.aba.industry.router.client.impl.IndustrialCalculatorRouterClientImpl;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by maurerit on 8/7/16.
 */
@Component
public class BuildCalculationRequestResponder implements RequestResponder<BuildCalculationRequest> {
    private static final Logger logger = LoggerFactory.getLogger( BuildCalculationRequestResponder.class );

    @Autowired
    private ApplicationContext context;

    @Autowired
    private SlackSession session;

    @Async( "threadPoolTaskExecutor" )
    @Override
    public void respond ( SlackMessagePosted slackMessage, BuildCalculationRequest buildCalculationRequest ) {
        logger.debug( "Received a build calculation request: {}", buildCalculationRequest );
        IndustrialCalculatorRouterClientImpl routerClient = context.getBean(
                IndustrialCalculatorRouterClientImpl.class );
        BuildCalculationResult result = null;

        try {
            result = routerClient.calculateBuild( buildCalculationRequest );
            routerClient.disconnect();
        }
        catch ( IOException e ) {
            throw new AsyncSlackException( session, slackMessage, e );
        }

        if ( result == null ) {
            NullPointerException npe = new NullPointerException();
            npe.setStackTrace( npe.fillInStackTrace()
                                  .getStackTrace() );
            throw new AsyncSlackException( session, slackMessage, npe );
        }

        StringBuilder message = new StringBuilder();
        message.append( MessageUtils.formatUserForClicky( slackMessage.getSender() ) )
               .append( "\n" )
               .append( MessageUtils.formatBuildCalculationResult( result ) );

        session.sendMessage( slackMessage.getChannel(), message.toString() );
    }
}
