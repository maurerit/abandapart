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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZMsg;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith( SpringJUnit4ClassRunner.class )
@SpringApplicationConfiguration( IndustryDirectorConnectionTests.class )
@EnableAutoConfiguration
@ComponentScan( "com.aba" )
public class IndustryDirectorConnectionTests {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private IndustryDirectorConnection connection;

    private Context context = ZMQ.context( 1 );

    private Socket receiver;

    private Socket sender;

    @Value( "${aba.industry.director.location}" )
    private String location;
    @Value( "${aba.industry.director.protocol}" )
    private String protocol;

    @Value( "${aba.industry.director.receivePort}" )
    private String receivePort;
    @Value( "${aba.industry.director.publishPort}" )
    private String publishPort;

    @Test
    public void testSendMessage ( ) throws IOException {
        TestJsonString expectedMessage = new TestJsonString( "TEST BODY" );

        connection.sendMessage( "TEST BODY", expectedMessage );

        ZMsg msg = ZMsg.recvMsg( receiver );
        String message = new String( msg.getLast()
                                        .getData(), ZMQ.CHARSET ).trim();

        TestJsonString receivedMessage = objectMapper.readValue( message, TestJsonString.class );

        assertEquals( "Messages should be the same", expectedMessage, receivedMessage );
    }

    @Before
    public void initialize ( ) {
        if ( receiver == null ) {
            this.receiver = context.socket( zmq.ZMQ.ZMQ_PULL );
            this.sender = context.socket( zmq.ZMQ.ZMQ_PUB );

            this.receiver.bind( protocol + "://*:" + receivePort );
            System.out.println( "Bound 'director' receiver to: " + protocol + "://*:" + receivePort );
            this.sender.bind( protocol + "://*:" + publishPort );
            System.out.println( "Bound 'director' sender to: " + protocol + "://*:" + publishPort );
        }
    }

    @After
    public void tearDown ( ) {
        sender.close();
        receiver.close();
        context.term();
    }
}
