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
//  Task sink in Java
//  Binds PULL socket to tcp://localhost:5558
//  Collects results from workers via that socket
//
public class tasksink {

    public static void main ( String[] args ) throws Exception {

        //  Prepare our context and socket
        ZMQ.Context context = ZMQ.context( 1 );
        ZMQ.Socket receiver = context.socket( ZMQ.PULL );
        receiver.bind( "tcp://*:5558" );

        //  Wait for start of batch
        String string = new String( receiver.recv( 0 ), ZMQ.CHARSET );

        //  Start our clock now
        long tstart = System.currentTimeMillis();

        //  Process 100 confirmations
        int task_nbr;
        int total_msec = 0;     //  Total calculated cost in msecs
        for ( task_nbr = 0; task_nbr < 100; task_nbr++ ) {
            string = new String( receiver.recv( 0 ), ZMQ.CHARSET ).trim();
            if ( ( task_nbr / 10 ) * 10 == task_nbr ) {
                System.out.print( ":" );
            }
            else {
                System.out.print( "." );
            }
        }
        //  Calculate and report duration of batch
        long tend = System.currentTimeMillis();

        System.out.println( "\nTotal elapsed time: " + ( tend - tstart ) + " msec" );
        receiver.close();
        context.term();
    }
}