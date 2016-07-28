/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.bus;

import com.aba.data.TypedJsonMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.zeromq.ZContext;
import org.zeromq.ZMQ.Socket;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Initializes and manages connections to the Industry Director.
 * <p>
 * Provides methods for easily sending and receiving messages.
 *
 * @author Marq Aideron <marq.aideron@gmail.com>
 */
@Component
@ConfigurationProperties( "aba.industry.director" )
public class IndustryDirectorConnectionImpl implements IndustryDirectorConnection {
    private static final Logger       logger       = LoggerFactory.getLogger( IndustryDirectorConnection.class );
    private              ObjectMapper objectMapper = new ObjectMapper();

    /**
     * The dns location of the industry director
     */
    @Value( "${aba.industry.director.location}" )
    private String location;

    /**
     * The protocol on which the director speaks
     */
    @Value( "${aba.industry.director.protocol}" )
    private String protocol;

    /**
     * The port on which the director listens
     */
    @Value( "${aba.industry.director.receivePort}" )
    private String directorreceiverPort;

    /**
     * The port on which the director will publish things
     */
    @Value( "${aba.industry.director.publishPort}" )
    private String directorPublishPort;

    private ZContext context = new ZContext();

    private Socket pushToDirector;

    private Socket subscriptionReceive;

    public String requestReply ( TypedJsonMessage message ) {
        return null;
    }

    public void sendMessage ( String topic, TypedJsonMessage messageBody ) throws JsonProcessingException {
        Assert.notNull( messageBody, "Message Body is null, cannot send that" );

        sendMessage( topic, objectMapper.writeValueAsString( messageBody ) );
    }

    private void sendMessage ( String topic, String messageBody ) {
        pushToDirector.sendMore( topic );
        pushToDirector.send( messageBody );
    }

    public void subscribe ( String topic, IndustryMessageHandler handler ) {
        Assert.notNull( handler, "Handler cannot be null" );
        subscriptionReceive.subscribe( topic.getBytes() );
    }

    @PostConstruct
    public void initialize ( ) {
        Assert.notNull( location, "Director location not set" );
        Assert.notNull( protocol, "Protocol for director not set" );
        Assert.notNull( directorreceiverPort, "Port for director to receive not set" );
        Assert.notNull( directorPublishPort, "Port for director to send from not set" );

        this.pushToDirector = context.createSocket( zmq.ZMQ.ZMQ_PUSH );
        this.subscriptionReceive = context.createSocket( zmq.ZMQ.ZMQ_SUB );

        this.pushToDirector.connect( protocol + "://" + location + ":" + directorreceiverPort );
        logger.info( "Bound sender using: {}", protocol + "://" + location + ":" + directorreceiverPort );
        this.subscriptionReceive.connect( protocol + "://" + location + ":" + directorPublishPort );
        logger.info( "Bound receiver using: {}", protocol + "://" + location + ":" + directorPublishPort );
    }

    @PreDestroy
    public void destroy ( ) {
        pushToDirector.close();
        subscriptionReceive.close();
        context.close();
    }
}
