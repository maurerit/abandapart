package com.aba.industry.bus;

public interface IndustryDirectorConnection {
	void sendMessage ( String topic, Object messageBody );
}
