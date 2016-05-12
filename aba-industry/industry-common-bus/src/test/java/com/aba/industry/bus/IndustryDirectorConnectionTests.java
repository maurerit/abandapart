package com.aba.industry.bus;

import static org.junit.Assert.assertEquals;

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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IndustryDirectorConnectionTests.class)
@EnableAutoConfiguration
@ComponentScan("com.aba")
public class IndustryDirectorConnectionTests {

	@Autowired
	private IndustryDirectorConnection connection;
	
	private Context context = ZMQ.context(1);
	
	private Socket receiver;
	
	private Socket sender;
	
	@Value("${aba.industry.director.location}")
	private String location;
	@Value("${aba.industry.director.protocol}")
	private String protocol;
	
	@Value("${aba.industry.director.receivePort}")
	private String receivePort;
	@Value("${aba.industry.director.publishPort}")
	private String publishPort;
	
	@Test
	public void testSendMessage() {
		String expectedMessage = "TEST BODY";
		
		connection.sendMessage("TEST BODY", expectedMessage);
		
		String receivedMessage = new String(receiver.recv(0), ZMQ.CHARSET).trim();
		
		assertEquals("Messages should be the same", expectedMessage, receivedMessage);
	}

	@Before
	public void initialize ( ) {
		if ( receiver == null ) {
			this.receiver = context.socket(zmq.ZMQ.ZMQ_PULL);
			this.sender = context.socket(zmq.ZMQ.ZMQ_PUB);
			
			this.receiver.bind(protocol + "://*:" + receivePort);
			System.out.println("Bound 'director' receiver to: " + protocol + "://*:" + receivePort);
			this.sender.bind(protocol + "://*:" + publishPort);
			System.out.println("Bound 'director' sender to: " + protocol + "://*:" + publishPort);
		}
	}
	
	@After
	public void tearDown ( ) {
		sender.close();
		receiver.close();
		context.term();
	}
}
