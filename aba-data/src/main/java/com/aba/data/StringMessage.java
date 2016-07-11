package com.aba.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class StringMessage extends TypedJsonMessage {
	private String contents;

	public StringMessage(String contents) {
		super();
		this.contents = contents;
	}
}
