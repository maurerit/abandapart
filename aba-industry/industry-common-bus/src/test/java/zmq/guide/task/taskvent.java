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

import java.util.Random;

//
//  Task ventilator in Java
//  Binds PUSH socket to tcp://localhost:5557
//  Sends batch of tasks to workers via that socket
//
public class taskvent {

    public static void main ( String[] args ) throws Exception {
        ZMQ.Context context = ZMQ.context( 1 );

        //  Socket to send messages on
        ZMQ.Socket sender = context.socket( ZMQ.PUSH );
        sender.bind( "tcp://*:5557" );

        //  Socket to send messages on
        ZMQ.Socket sink = context.socket( ZMQ.PUSH );
        sink.connect( "tcp://localhost:5558" );

        System.out.println( "Press Enter when the workers are ready: " );
        System.in.read();
        System.out.println( "Sending tasks to workers\n" );

        //  The first message is "0" and signals start of batch
        sink.send( "0", 0 );

        //  Initialize random number generator
        Random srandom = new Random( System.currentTimeMillis() );

        //  Send 100 tasks
        int task_nbr;
        int total_msec = 0;     //  Total expected cost in msecs
        for ( task_nbr = 0; task_nbr < 100; task_nbr++ ) {
            int workload;
            //  Random workload from 1 to 100msecs
            workload = srandom.nextInt( 100 ) + 1;
            total_msec += workload;
            System.out.print( workload + "." );
            String string = String.format( "%d", workload );
            sender.send( string, 0 );
        }
        System.out.println( "Total expected cost: " + total_msec + " msec" );
        Thread.sleep( 1000 );              //  Give 0MQ time to deliver

        sink.close();
        sender.close();
        context.term();
    }
}