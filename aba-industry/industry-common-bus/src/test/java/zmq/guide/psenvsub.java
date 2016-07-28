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

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

/**
 * Pubsub envelope subscriber
 */

public class psenvsub {

    public static void main ( String[] args ) {

        // Prepare our context and subscriber
        Context context = ZMQ.context( 1 );
        Socket subscriber = context.socket( ZMQ.SUB );

        subscriber.connect( "tcp://localhost:5563" );
        subscriber.subscribe( "B".getBytes( ZMQ.CHARSET ) );
        while ( !Thread.currentThread()
                       .isInterrupted() ) {
            // Read envelope with address
            String address = subscriber.recvStr();
            // Read message contents
            String contents = subscriber.recvStr();
            System.out.println( address + " : " + contents );
        }
        subscriber.close();
        context.term();
    }
}