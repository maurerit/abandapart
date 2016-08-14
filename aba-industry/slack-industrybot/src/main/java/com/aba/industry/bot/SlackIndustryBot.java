/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.bot;

import com.aba.TypeIdNotFoundException;
import com.aba.data.domain.config.IndustrySkillConfiguration;
import com.aba.data.domain.config.InventionSkillConfiguration;
import com.aba.industry.bot.responder.RequestResponder;
import com.aba.industry.bot.responder.impl.ExceptionErrorResponder;
import com.aba.industry.bot.responder.impl.TypeIdNotFoundResponder;
import com.aba.industry.bus.model.BuildCalculationRequest;
import com.aba.industry.fetch.client.TypeNameToTypeIdProvider;
import com.aba.industry.router.client.impl.IndustrialCalculatorRouterClientImpl;
import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackPersona;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.SlackUser;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * Created by maurerit on 8/7/16.
 */
@Component
public class SlackIndustryBot implements Runnable {
    public static final Integer SECONDS_BEFORE_SHUTDOWN = 5;

    private static final Logger logger = LoggerFactory.getLogger( SlackIndustryBot.class );

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private IndustrialCalculatorRouterClientImpl routerClient;

    @Autowired
    private TypeNameToTypeIdProvider typeIdProvider;

    @Autowired
    private ExceptionErrorResponder exceptionErrorResponder;

    @Autowired
    private TypeIdNotFoundResponder typeIdNotFoundResponder;

    //TODO: For some reason, referencing the BuildCalculationRequestResponder by fully qualified name fails :(.
    @Autowired
    private RequestResponder<BuildCalculationRequest> buildCalculationRequestResponder;

    @Autowired
    private SlackSession session;
    private boolean shuttingDown = false;
    private SlackPersona me;

    public void run ( ) {
        logger.info( "Starting up and adding message posted listener" );
        session.addMessagePostedListener( new MessageListener() );
        me = session.sessionPersona();

        Assert.notNull( me );

        while ( !shuttingDown ) {
            try {
                Thread.sleep( SECONDS_BEFORE_SHUTDOWN * 1000 );
            }
            catch ( InterruptedException e ) {
                try {
                    session.disconnect();
                }
                catch ( IOException e1 ) {
                    throw new RuntimeException( e1 );
                }
            }
        }

        try {
            logger.info( "Shutting down" );
            session.disconnect();
            //TODO: I don't like doing this but I think the spring threadPoolExecutor is keeping the jvm alive.
            System.exit( 0 );
        }
        catch ( IOException e ) {
            logger.error( "IOException received while closing slack connection", e );
        }
    }

    private void handleCalculateBuildRequest ( SlackMessagePosted event,
                                               CalculateCommands command ) throws IOException, TypeIdNotFoundException
    {
        String[] segments = command.getInterestingSegments( event.getMessageContent() );
        String typeName = segments[0];
        Integer typeId = typeIdProvider.getTypeIdForTypeName( typeName );

        if ( typeId.equals( -1 ) ) {
            throw new TypeIdNotFoundException( typeName, "No type id found" );
        }

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
        //FIXME: #29
        request.setMeLevel( 10 );
        request.setTeLevel( 20 );
        request.setRequestedBuildTypeId( typeId );
        request.setRequestedBuildTypeName( typeName );

        request.setSystemName( "Atreen" );

        buildCalculationRequestResponder.respond( event, request );
    }

    public final class MessageListener implements SlackMessagePostedListener {
        public static final String SHUTDOWN_CMD = "shutdown";

        @Override
        public void onEvent ( SlackMessagePosted event, SlackSession session ) {
            String message = event.getMessageContent();
            SlackUser sender = event.getSender();
            SlackChannel channel = event.getChannel();

            Assert.notNull( message );

            logger.debug( "{} said {}", sender.getRealName(), message );
            if ( message.contains( me.getId() ) ) {
                //Try to find a more advanced command first
                CalculateCommands command = CalculateCommands.findCommand( message );
                if ( command == null ) {
                    //Extremely basic commands
                    if ( message.contains( SHUTDOWN_CMD ) ) {
                        session.sendMessage( channel, "Shutting down in " + SECONDS_BEFORE_SHUTDOWN + " seconds." );
                        shuttingDown = true;
                    }
                    else if ( message.contains( "ello" ) ) {
                        StringBuilder sb = new StringBuilder();
                        sb.append( "Hello " )
                          .append( "<@" )
                          .append( sender.getId() )
                          .append( "|" )
                          .append( sender.getUserName() )
                          .append( ">!  How are you today?" );
                        session.sendMessage( channel, sb.toString() );
                    }
                    else if ( message.contains( "help" ) ) {
                        StringBuilder sb = new StringBuilder();
                        sb.append( "<@" )
                          .append( sender.getId() )
                          .append( "|" )
                          .append( sender.getUserName() )
                          .append( ">:\n" )
                          .append(
                                  "I understand the following commands.  Bolded segments are things that can vary " +
                                          "from request to request.\n" );
                        for ( CalculateCommands currentCommand : CalculateCommands.values() ) {
                            sb.append( currentCommand.getHelpText() );
                        }
                        session.sendMessage( channel, sb.toString() );
                    }
                }

                switch ( command ) {
                    case CALCULATE_BUILD_BASIC:
                        try {
                            handleCalculateBuildRequest( event, command );
                        }
                        catch ( IOException e ) {
                            exceptionErrorResponder.reportError( session, event, e.getLocalizedMessage(), e );
                        }
                        catch ( TypeIdNotFoundException e ) {
                            typeIdNotFoundResponder.reportError( session, event, e.getMessage(), e.getTypeName() );
                        }
                        break;
                    case CALCULATE_BUILD_SPECIFY_PROCURE_TYPE:
                        break;

                }
            }
        }
    }
}
