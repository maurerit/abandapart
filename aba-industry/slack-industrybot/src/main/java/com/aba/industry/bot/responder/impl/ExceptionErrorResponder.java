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

import com.aba.industry.bot.responder.ErrorResponder;
import com.aba.industry.bot.util.MessageUtils;
import com.ullink.slack.simpleslackapi.SlackMessageHandle;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.replies.SlackMessageReply;
import org.springframework.stereotype.Component;

/**
 * Created by maurerit on 8/9/16.
 */
@Component
public class ExceptionErrorResponder implements ErrorResponder<SlackMessageHandle<SlackMessageReply>, Exception> {
    @Override
    public void reportError ( SlackSession session, SlackMessagePosted event,
                              String problem, Exception e )
    {
        StringBuilder message = new StringBuilder();
        message.append( MessageUtils.formatUserForClicky( event.getSender() ) )
               .append( ": There was an issue completing your request :(\n\n" )
               .append( MessageUtils.formatStackTrace( e ) );

        SlackMessageHandle<SlackMessageReply> handle = session.sendMessage( event.getChannel(), message.toString() );
    }
}
