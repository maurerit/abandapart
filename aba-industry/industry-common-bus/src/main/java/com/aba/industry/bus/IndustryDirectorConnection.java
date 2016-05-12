package com.aba.industry.bus;

public interface IndustryDirectorConnection {
	public String requestReply ( String message );
	
	void sendMessage ( String topic, Object messageBody );
}
