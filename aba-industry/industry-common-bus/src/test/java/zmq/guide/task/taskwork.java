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

package zmq.guide.task;

import org.zeromq.ZMQ;

//
//  Task worker in Java
//  Connects PULL socket to tcp://localhost:5557
//  Collects workloads from ventilator via that socket
//  Connects PUSH socket to tcp://localhost:5558
//  Sends results to sink via that socket
//
public class taskwork {

    public static void main ( String[] args ) throws Exception {
        ZMQ.Context context = ZMQ.context( 1 );

        //  Socket to receive messages on
        ZMQ.Socket receiver = context.socket( ZMQ.PULL );
        receiver.connect( "tcp://localhost:5557" );

        //  Socket to send messages to
        ZMQ.Socket sender = context.socket( ZMQ.PUSH );
        sender.connect( "tcp://localhost:5558" );

        //  Process tasks forever
        while ( !Thread.currentThread()
                       .isInterrupted() ) {
            String string = new String( receiver.recv( 0 ), ZMQ.CHARSET ).trim();
            long msec = Long.parseLong( string );
            //  Simple progress indicator for the viewer
            System.out.flush();
            System.out.print( string + '.' );

            //  Do the work
            Thread.sleep( msec );

            //  Send results to sink
            sender.send( ZMQ.MESSAGE_SEPARATOR, 0 );
        }
        sender.close();
        receiver.close();
        context.term();
    }
}