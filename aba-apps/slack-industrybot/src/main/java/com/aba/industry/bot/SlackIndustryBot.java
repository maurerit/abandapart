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
import com.aba.industry.bot.commands.CalculateCommands;
import com.aba.industry.bot.commands.PreferencesCommands;
import com.aba.industry.bot.model.Preferences;
import com.aba.industry.bot.responder.RequestResponder;
import com.aba.industry.bot.responder.impl.ExceptionErrorResponder;
import com.aba.industry.bot.responder.impl.TypeIdNotFoundResponder;
import com.aba.industry.bot.service.PreferencesService;
import com.aba.industry.bot.util.MessageUtils;
import com.aba.industry.fetch.client.TypeNameToTypeIdProvider;
import com.aba.industry.model.BuildCalculationRequest;
import com.aba.industry.service.IndustryCalculationService;
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
import java.util.stream.Collectors;

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
    private IndustryCalculationService routerClient;

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
    private PreferencesService preferencesService;

    @Autowired
    private SlackSession session;
    private boolean shuttingDown = false;
    private SlackPersona me;

    public void run ( )
    {
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
        Preferences prefs = preferencesService.getPreferencesForUser( event.getSender()
                                                                           .getId() );

        String[] segments = command.getInterestingSegments( event.getMessageContent() );
        String typeName = segments[0];
        Integer typeId = typeIdProvider.getTypeIdForTypeName( typeName );

        BuildCalculationRequest request = new BuildCalculationRequest();

        request.setIndustrySkills( prefs.getIndustrySkillConfiguration() );
        request.setInventionSkills( prefs.getInventionSkillConfiguration() );
        request.setSuppressSalary( prefs.getSuppressSalary() );
        request.setSuppressFreight( prefs.getSuppressFreight() );
        request.setSuppressInstallation( prefs.getSuppressInstallation() );

        request.setBuildOrBuyConfigurationList( prefs.getBuildOrBuyConfigurations()
                                                     .entrySet()
                                                     .stream()
                                                     .map( entry -> entry.getValue() )
                                                     .collect(
                                                             Collectors.toList() ) );
        request.setUseBuildOrBuyConfigurations(
                request.getBuildOrBuyConfigurationList() != null && request.getBuildOrBuyConfigurationList()
                                                                           .size() > 0 );

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
        public void onEvent ( SlackMessagePosted event, SlackSession session )
        {
            String message = event.getMessageContent();
            SlackUser sender = event.getSender();
            SlackChannel channel = event.getChannel();

            Assert.notNull( message );

            logger.debug( "{} said {}", sender.getRealName(), message );
            if ( message.contains( me.getId() ) ) {
                //Try to find a more advanced command first
                CalculateCommands calculateCommand = CalculateCommands.findCommand( message );
                PreferencesCommands preferencesCommand = PreferencesCommands.findCommand( message );
                //region Simple Commands
                if ( calculateCommand == null && preferencesCommand == null ) {
                    //Extremely basic commands
                    if ( message.contains( SHUTDOWN_CMD ) ) {
                        session.sendMessage( channel, "Shutting down in " + SECONDS_BEFORE_SHUTDOWN + " seconds." );
                        shuttingDown = true;
                    }
                    else if ( message.contains( "ello" ) ) {
                        StringBuilder sb = new StringBuilder();
                        sb.append( "Hello " )
                          .append( MessageUtils.formatUserForClicky( event.getSender() ) )
                          .append( "!  How are you today?" );
                        session.sendMessage( channel, sb.toString() );
                    }
                    else if ( message.contains( "help" ) ) {
                        StringBuilder sb = new StringBuilder();
                        sb.append( MessageUtils.formatUserForClicky( event.getSender() ) )
                          .append( ":" )
                          .append(
                                  "\nI understand the following commands.  Bolded segments are things that can vary " +
                                          "from request to request.\n" );
                        sb.append( "Calculation:\n" );
                        for ( CalculateCommands currentCommand : CalculateCommands.values() ) {
                            sb.append( currentCommand.getHelpText() );
                        }
                        sb.append( "\nPreferences:\n" );
                        for ( PreferencesCommands currentCommand : PreferencesCommands.values() ) {
                            sb.append( currentCommand.getHelpText() );
                        }
                        session.sendMessage( channel, sb.toString() );
                    }
                    else if ( message.contains( ":heart:" ) ) {
                        StringBuilder sb = new StringBuilder();
                        sb.append( MessageUtils.formatUserForClicky( event.getSender() ) )
                          .append( " I :heart: you too!" );
                        session.sendMessage( channel, sb.toString() );
                    }
                    else if ( ( message.contains( "bacon" ) || message.contains( "Bacon" ) || message.contains(
                            "BACON" ) ) && event.getSender()
                                                .getRealName()
                                                .equals( "Argos Gelert" ) )
                    {
                        StringBuilder sb = new StringBuilder();
                        sb.append( "I'm sorry " )
                          .append( MessageUtils.formatUserForClicky( event.getSender() ) )
                          .append(
                                  ", I've been instructed to not talk about Bacon around you :(.  It's for your own " +
                                          "good you know.  But if you insist! https://media.giphy" +
                                          ".com/media/c3RSmXUnz2l3O/giphy.gif" );
                        session.sendMessage( channel, sb.toString() );
                    }
                }
                //endregion

                if ( calculateCommand != null ) {
                    switch ( calculateCommand ) {
                        case CALCULATE_BUILD_BASIC:
                            try {
                                handleCalculateBuildRequest( event, calculateCommand );
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
                else if ( preferencesCommand != null ) {
                    preferencesService.processCommand( preferencesCommand, event );
                }
            }
        }
    }
}
