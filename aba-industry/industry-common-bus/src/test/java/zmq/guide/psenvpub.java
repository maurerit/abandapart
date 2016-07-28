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
 * Pubsub envelope publisher
 */

public class psenvpub {

    public static void main ( String[] args ) throws Exception {
        // Prepare our context and publisher
        Context context = ZMQ.context( 1 );
        Socket publisher = context.socket( ZMQ.PUB );

        publisher.bind( "tcp://*:5563" );
        while ( !Thread.currentThread()
                       .isInterrupted() ) {
            // Write two messages, each with an envelope and content
            publisher.sendMore( "A" );
            publisher.send( "We don't want to see this" );
            publisher.sendMore( "B" );
            publisher.send( "We would like to see this" );
        }
        publisher.close();
        context.term();
    }
}