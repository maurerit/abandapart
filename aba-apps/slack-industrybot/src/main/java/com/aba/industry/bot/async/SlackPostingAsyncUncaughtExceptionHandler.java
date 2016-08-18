/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.bot.async;

import com.aba.industry.bot.util.MessageUtils;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * Created by maurerit on 8/9/16.
 */
public class SlackPostingAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger( SlackPostingAsyncUncaughtExceptionHandler.class );

    @Override
    public void handleUncaughtException ( Throwable ex, Method method, Object... params ) {
        if ( ex instanceof AsyncSlackException ) {
            SlackMessagePosted slackMessage = ( (AsyncSlackException) ex ).getSlackMessage();
            SlackSession session = ( (AsyncSlackException) ex ).getSession();

            StringBuilder message = new StringBuilder();
            message.append( "Hey " )
                   .append( MessageUtils.formatUserForClicky( slackMessage.getSender() ) )
                   .append( " I was not able to finish your request because I got a little drunk and:\n" )
                   .append( MessageUtils.formatStackTrace( ex.getCause() ) )
                   .append( "\n\nhappened all over my floor :(." );

            session.sendMessage( slackMessage.getChannel(), message.toString() );
        }
        else {
            logger.error( "Caught and swallowed {} from {}", ex, method );
        }
    }
}
