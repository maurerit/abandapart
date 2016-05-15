package com.aba.industry.bus;

import com.aba.data.TypedJsonMessage;

public interface IndustryMessageHandler {
	public void messageReceived ( String topic, TypedJsonMessage messageBody );
}
