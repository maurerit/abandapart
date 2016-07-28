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

package zmq.guide;

import org.zeromq.*;
import org.zeromq.ZMQ.PollItem;
import org.zeromq.ZMQ.Socket;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Load-balancing broker
 * Demonstrates use of the ZLoop API and reactor style
 * <p>
 * The client and worker tasks are identical from the previous example.
 */
public class lbbroker3 {
    private static final int             NBR_CLIENTS        = 1;
    private static final int             NBR_WORKERS        = 20;
    private static final int             NUMBER_OF_MESSAGES = 20000;
    private final static FrontendHandler frontendHandler    = new FrontendHandler();
    private final static BackendHandler  backendHandler     = new BackendHandler();
    private static       byte[]          WORKER_READY       = { '\001' };

    /**
     * And the main task now sets-up child tasks, then starts its reactor.
     * If you press Ctrl-C, the reactor exits and the main task shuts down.
     */
    public static void main ( String[] args ) {
        ZContext context = new ZContext();
        LBBroker arg = new LBBroker();
        //  Prepare our context and sockets
        arg.frontend = context.createSocket( ZMQ.ROUTER );
        arg.backend = context.createSocket( ZMQ.ROUTER );
        arg.frontend.bind( "ipc://frontend.ipc" );
        arg.backend.bind( "ipc://backend.ipc" );

        int clientNbr;
        for ( clientNbr = 0; clientNbr < NBR_CLIENTS; clientNbr++ ) {
            ZThread.start( new ClientTask() );
        }

        for ( int workerNbr = 0; workerNbr < NBR_WORKERS; workerNbr++ ) {
            ZThread.start( new WorkerTask() );
        }

        //  Queue of available workers
        arg.workers = new LinkedList<ZFrame>();

        //  Prepare reactor and fire it up
        ZLoop reactor = new ZLoop();
        PollItem item = new PollItem( arg.backend, ZMQ.Poller.POLLIN );
        reactor.addPoller( item, backendHandler, arg );
        reactor.start();

        context.destroy();
    }

    ;

    /**
     * Basic request-reply client using REQ socket
     */
    private static class ClientTask implements ZThread.IDetachedRunnable {
        @Override
        public void run ( Object... args ) {
            ZContext context = new ZContext();

            //  Prepare our context and sockets
            Socket client = context.createSocket( ZMQ.REQ );
            ZHelper.setId( client );     //  Set a printable identity

            client.connect( "ipc://frontend.ipc" );

            for ( int idx = 0; idx < NUMBER_OF_MESSAGES; idx++ ) {
                //  Send request, get reply
                client.send( "HELLO" );
                String reply = client.recvStr();
                System.out.println( "Client: " + reply );

                try {
                    Thread.sleep( 500 );
                }
                catch ( InterruptedException e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            context.destroy();
        }
    }

    /**
     * Worker using REQ socket to do load-balancing
     */
    private static class WorkerTask implements ZThread.IDetachedRunnable {
        @Override
        public void run ( Object... args ) {
            ZContext context = new ZContext();

            //  Prepare our context and sockets
            Socket worker = context.createSocket( ZMQ.REQ );
            ZHelper.setId( worker );     //  Set a printable identity

            worker.connect( "ipc://backend.ipc" );

            //  Tell backend we're ready for work
            ZFrame frame = new ZFrame( WORKER_READY );
            frame.send( worker, 0 );

            while ( true ) {
                ZMsg msg = ZMsg.recvMsg( worker );
                if ( msg == null ) {
                    break;
                }

                msg.getLast()
                   .reset( "OK" );
                msg.send( worker );
            }
            context.destroy();
        }
    }

    //Our load-balancer structure, passed to reactor handlers
    private static class LBBroker {
        Socket        frontend;             //  Listen to clients
        Socket        backend;              //  Listen to workers
        Queue<ZFrame> workers;       //  List of ready workers
    }

    /**
     * In the reactor design, each time a message arrives on a socket, the
     * reactor passes it to a handler function. We have two handlers; one
     * for the frontend, one for the backend:
     */
    private static class FrontendHandler implements ZLoop.IZLoopHandler {

        @Override
        public int handle ( ZLoop loop, PollItem item, Object arg_ ) {

            LBBroker arg = (LBBroker) arg_;
            ZMsg msg = ZMsg.recvMsg( arg.frontend );
            if ( msg != null ) {
                msg.wrap( arg.workers.poll() );
                msg.send( arg.backend );

                //  Cancel reader on frontend if we went from 1 to 0 workers
                if ( arg.workers.size() == 0 ) {
                    loop.removePoller( new PollItem( arg.frontend, 0 ) );
                }
            }
            return 0;
        }

    }

    private static class BackendHandler implements ZLoop.IZLoopHandler {

        @Override
        public int handle ( ZLoop loop, PollItem item, Object arg_ ) {

            LBBroker arg = (LBBroker) arg_;
            ZMsg msg = ZMsg.recvMsg( arg.backend );
            if ( msg != null ) {
                ZFrame address = msg.unwrap();
                //  Queue worker address for load-balancing
                arg.workers.add( address );

                //  Enable reader on frontend if we went from 0 to 1 workers
                if ( arg.workers.size() == 1 ) {
                    PollItem newItem = new PollItem( arg.frontend, ZMQ.Poller.POLLIN );
                    loop.addPoller( newItem, frontendHandler, arg );
                }

                //  Forward message to client if it's not a READY
                ZFrame frame = msg.getFirst();
                if ( Arrays.equals( frame.getData(), WORKER_READY ) ) {
                    msg.destroy();
                }
                else {
                    msg.send( arg.frontend );
                }
            }
            return 0;
        }
    }

}