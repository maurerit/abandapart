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

import com.aba.industry.bot.async.AsynSlackException;
import com.aba.industry.bot.responder.RequestResponder;
import com.aba.industry.bot.util.MessageUtils;
import com.aba.industry.bus.model.BuildCalculationRequest;
import com.aba.industry.model.BuildCalculationResult;
import com.aba.industry.router.client.impl.IndustrialCalculatorRouterClientImpl;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;

/**
 * Created by maurerit on 8/7/16.
 */
public class BuildCalculationRequestResponder implements RequestResponder<BuildCalculationRequest,
        BuildCalculationResult> {
    @Autowired
    IndustrialCalculatorRouterClientImpl routerClient;

    @Autowired
    private SlackSession session;

    @Async
    @Override
    public void respond ( SlackMessagePosted slackMessage, BuildCalculationRequest buildCalculationRequest ) {
        BuildCalculationResult result = null;

        try {
            result = routerClient.calculateBuild( buildCalculationRequest );
        }
        catch ( IOException e ) {
            throw new AsynSlackException( slackMessage, e );
        }

        StringBuilder message = new StringBuilder();
        message.append( MessageUtils.formatUserForClicky( slackMessage.getSender() ) )
               .append( ": I have finished your build calc request for a " )
               .append( buildCalculationRequest.getRequestedBuildTypeName() )
               .append( MessageUtils.formatBuildCalculationResult( result ) );

        session.sendMessage( slackMessage.getChannel(), message.toString() );
    }
}
