/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.zeromq.ZMQ;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by maurerit on 8/6/16.
 */
@Component
public class IndustryRouter implements Runnable {
    public static final Logger  logger        = LoggerFactory.getLogger( IndustryRouter.class );
    public static final Integer FRONTEND_PORT = 42001;
    public static final Integer BACKEND_PORT  = 42000;

    //TODO: I don't FULLY understand this code but it SHOULD work for what I want it to do... hopefully.
    public void run ( ) {
        ZMQ.Context context = ZMQ.context( 1 );
        //  Prepare our context and sockets
        ZMQ.Socket frontend = context.socket( ZMQ.ROUTER );
        ZMQ.Socket backend = context.socket( ZMQ.ROUTER );
        frontend.bind( "tcp://0.0.0.0:" + FRONTEND_PORT );
        backend.bind( "tcp://0.0.0.0:" + BACKEND_PORT );

        logger.info( "Started frontend: {} and backend: {} queues... READY!", FRONTEND_PORT, BACKEND_PORT );

        //  Queue of available workers
        Queue<String> workerQueue = new LinkedList<String>();
        int messageCount = 0;

        while ( !Thread.currentThread()
                       .isInterrupted() ) {

            //  Initialize poll set
            ZMQ.Poller items = new ZMQ.Poller( 2 );

            //  Always poll for worker activity on backend
            items.register( backend, ZMQ.Poller.POLLIN );

            //  Poll front-end only if we have available workers
            if ( workerQueue.size() > 0 ) {
                items.register( frontend, ZMQ.Poller.POLLIN );
            }

            if ( items.poll() < 0 ) {
                break;      //  Interrupted
            }

            //  Handle worker activity on backend
            if ( items.pollin( 0 ) ) {

                //  Queue worker address for LRU routing
                String workerAddress = backend.recvStr();
                workerQueue.add( workerAddress );

                //  Second frame is empty
                String empty = backend.recvStr();
                assert ( empty.length() == 0 );

                //  Third frame is READY or else a client reply address
                String clientAddr = backend.recvStr();

                //  If client reply, send rest back to frontend
                if ( !clientAddr.equals( "READY" ) ) {
                    logger.debug( "Received READY from worker {}", workerAddress );
                    empty = backend.recvStr();
                    assert ( empty.length() == 0 );

                    String reply = backend.recvStr();
                    frontend.sendMore( clientAddr );
                    frontend.sendMore( "" );
                    frontend.send( reply );
                }

            }

            if ( items.pollin( 1 ) ) {
                //  Now get next client request, route to LRU worker
                //  Client request is [address][empty][request]
                String clientAddr = frontend.recvStr();

                String empty = frontend.recvStr();
                assert ( empty.length() == 0 );

                String request = frontend.recvStr();

                String workerAddr = workerQueue.poll();

                backend.sendMore( workerAddr );
                backend.sendMore( "" );
                backend.sendMore( clientAddr );
                backend.sendMore( "" );
                backend.send( request );
                messageCount++;

                if ( messageCount % 1000 == 1 ) {
                    logger.info( "Broker passed: {} messages.", messageCount );
                }

            }
        }

        frontend.close();
        backend.close();
        context.term();
    }
}
