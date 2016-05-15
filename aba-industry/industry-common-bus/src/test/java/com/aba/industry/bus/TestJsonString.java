package com.aba.industry.bus;

import com.aba.data.TypedJsonMessage;

public class TestJsonString extends TypedJsonMessage {
	public String contents;

	public TestJsonString ( ) { }
	
	public TestJsonString(String contents) {
		super();
		this.contents = contents;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestJsonString other = (TestJsonString) obj;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		return true;
	}
}
