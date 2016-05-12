package com.aba.industry.bus;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.zeromq.ZContext;
import org.zeromq.ZMQ.Socket;

/**
 * Initializes and manages connections to the Industry Director.
 * 
 * Provides methods for easily sending and receiving messages.
 * 
 * @author Marq Aideron <marq.aideron@gmail.com>
 */
@Component
@ConfigurationProperties("aba.industry.director")
public class IndustryDirectorConnectionImpl implements IndustryDirectorConnection {
	private static final Logger logger = LoggerFactory.getLogger(IndustryDirectorConnection.class);
	/**
	 * The dns location of the industry director
	 */
	@Value("${aba.industry.director.location}")
	private String location;
	
	/**
	 * The protocol on which the director speaks
	 */
	@Value("${aba.industry.director.protocol}")
	private String protocol;
	
	/**
	 * The port on which the director listens
	 */
	@Value("${aba.industry.director.receivePort}")
	private String directorreceiverPort;
	
	/**
	 * The port on which the director will publish things
	 */
	@Value("${aba.industry.director.publishPort}")
	private String directorPublishPort;
	
	private ZContext context = new ZContext();
	
	private Socket sender;
	
	private Socket receiver;
	
	public String requestReply ( String message ) {
		return null;
	}
	
	public void sendMessage ( String topic, Object messageBody ) {
		Assert.notNull(messageBody, "Message Body is null, cannot send that");
		
		sendMessage(topic, messageBody.toString());
	}
	
	private void sendMessage ( String topic, String messageBody ) {
		sender.sendMore(topic);
		sender.send(messageBody);
	}
	
	public void subscribe ( String topic, IndustryMessageHandler handler ) {
		Assert.notNull(handler, "Handler cannot be null");
		receiver.subscribe(topic.getBytes());
	}
	
	@PostConstruct
	public void initialize ( ) {
		Assert.notNull(location, "Director location not set");
		Assert.notNull(protocol, "Protocol for director not set");
		Assert.notNull(directorreceiverPort, "Port for director to receive not set");
		Assert.notNull(directorPublishPort, "Port for director to send from not set");
		
		this.sender = context.createSocket(zmq.ZMQ.ZMQ_PUSH);
		this.receiver = context.createSocket(zmq.ZMQ.ZMQ_SUB);
		
		this.sender.connect(protocol + "://" + location + ":" + directorreceiverPort);
		logger.info("Bound sender using: {}", protocol + "://" + location + ":" + directorreceiverPort);
		this.receiver.connect(protocol + "://" + location + ":" + directorPublishPort);
		logger.info("Bound receiver using: {}", protocol + "://" + location + ":" + directorPublishPort);
	}
	
	@PreDestroy
	public void destroy ( ) {
		sender.close();
		receiver.close();
		context.close();
	}
}
