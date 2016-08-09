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

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackPersona;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.SlackUser;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        }
        catch ( IOException e ) {
            logger.error( "IOException received while closing slack connection", e );
        }
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
                        break;
                    case CALCULATE_BUILD_SPECIFY_PROCURE_TYPE:
                        break;

                }
            }
        }
    }
}
