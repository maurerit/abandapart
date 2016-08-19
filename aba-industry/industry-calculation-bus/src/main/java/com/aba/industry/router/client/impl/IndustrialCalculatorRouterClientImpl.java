/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.router.client.impl;

import com.aba.industry.model.BuildCalculationRequest;
import com.aba.industry.model.BuildCalculationResult;
import com.aba.industry.router.client.IndustryCalculatorRouterClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.zeromq.ZMQ;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * Created by maurerit on 8/8/16.
 */
@ConfigurationProperties( prefix = "aba.industry.bus.router" )
public class IndustrialCalculatorRouterClientImpl implements IndustryCalculatorRouterClient {
    private static final Logger logger = LoggerFactory.getLogger( IndustrialCalculatorRouterClientImpl.class );
    @Value( "${aba.industry.bus.router.location}" )
    private String  routerLocation;
    @Value( "${aba.industry.bus.router.protocol}" )
    private String  routerProtocol;
    @Value( "${aba.industry.bus.router.port}" )
    private Integer routerPort;

    private String myName;

    private ObjectMapper mapper = new ObjectMapper();
    private ZMQ.Context context;
    private ZMQ.Socket  socket;

    @Override
    public BuildCalculationResult calculateBuild ( BuildCalculationRequest request ) throws IOException
    {
        BuildCalculationResult result = null;

        String requestStr = mapper.writeValueAsString( request );

        socket.send( requestStr );
        String resultStr = socket.recvStr();

        if ( resultStr != null ) {
            result = mapper.readValue( resultStr, BuildCalculationResult.class );
        }

        return result;
    }

    @Override
    public void disconnect ( ) {
        socket.close();
        context.term();
    }

    @PostConstruct
    public void initialize ( ) {
        context = ZMQ.context( 1 );
        socket = context.socket( ZMQ.REQ );
        myName = Thread.currentThread()
                       .getName() + "@" + ManagementFactory.getRuntimeMXBean()
                                                           .getName();

        socket.setIdentity( myName.getBytes( ZMQ.CHARSET ) );

        socket.connect( routerProtocol + "://" + routerLocation + ":" + routerPort );
        socket.setReceiveTimeOut( 120000 );
        logger.debug( "{} initialized with with routerLocation {} routerProtocol {} routerPort {}",
                      IndustrialCalculatorRouterClientImpl.class.getName(), routerLocation, routerProtocol,
                      routerPort );
    }
}
