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

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ZHelper {
    private static Random rand = new Random( System.currentTimeMillis() );

    /**
     * Receives all message parts from socket, prints neatly
     */
    public static void dump ( Socket sock ) {
        System.out.println( "----------------------------------------" );
        while ( true ) {
            byte[] msg = sock.recv( 0 );
            boolean isText = true;
            String data = "";
            for ( int i = 0; i < msg.length; i++ ) {
                if ( msg[i] < 32 || msg[i] > 127 ) {
                    isText = false;
                }
                data += String.format( "%02X", msg[i] );
            }
            if ( isText ) {
                data = new String( msg, ZMQ.CHARSET );
            }

            System.out.println( String.format( "[%03d] %s", msg.length, data ) );
            if ( !sock.hasReceiveMore() ) {
                break;
            }
        }
    }

    public static void setId ( Socket sock ) {
        String identity = String.format( "%04X-%04X", rand.nextInt(), rand.nextInt() );

        sock.setIdentity( identity.getBytes( ZMQ.CHARSET ) );
    }

    public static List<Socket> buildZPipe ( Context ctx ) {
        Socket socket1 = ctx.socket( ZMQ.PAIR );
        socket1.setLinger( 0 );
        socket1.setHWM( 1 );

        Socket socket2 = ctx.socket( ZMQ.PAIR );
        socket2.setLinger( 0 );
        socket2.setHWM( 1 );

        String iface = "inproc://" + new BigInteger( 130, rand ).toString( 32 );
        socket1.bind( iface );
        socket2.connect( iface );

        return Arrays.asList( socket1, socket2 );
    }
}