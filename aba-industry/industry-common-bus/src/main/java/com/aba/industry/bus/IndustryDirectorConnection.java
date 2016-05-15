package com.aba.industry.bus;

import com.aba.data.TypedJsonMessage;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IndustryDirectorConnection {
	/**
	 * Push a message to the router connection that the industry-director has specifically
	 * for clients to request data from some other services bound up to the service provider
	 * router connection.
	 * 
	 * @param message
	 * @return
	 */
	public String requestReply ( TypedJsonMessage message ) throws JsonProcessingException;
	
	/**
	 * Publish a message for all who might be subscribing to the topic to retrieve.
	 * 
	 * @param topic
	 * @param messageBody
	 */
	void sendMessage ( String topic, TypedJsonMessage messageBody ) throws JsonProcessingException;
}
