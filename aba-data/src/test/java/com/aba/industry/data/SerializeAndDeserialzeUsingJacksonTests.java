package com.aba.industry.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A unit test just to illustrate the usage of deserializing a json object that has a property
 * within it that specifies what the java class it implements is.
 * 
 * @author maurerit
 */
public class SerializeAndDeserialzeUsingJacksonTests {
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	public void test() throws IOException, ClassNotFoundException {
		SampleDataType sdt = new SampleDataType();
		sdt.setId("jfkdajfldkafklsdjflafadsfjkadlsfjasl");
		sdt.setQuantity(200);
		sdt.setAmount(50000.01d);
		
		Map<String, String> keyValues = new HashMap<>();
		keyValues.put("Key1", "Value1");
		keyValues.put("Key2", "Value2");
		
		sdt.setKeyValues(keyValues);
		
		String json = objectMapper.writeValueAsString(sdt);
		
		System.out.println(json);
		
		JsonNode jsonNode = objectMapper.readTree(json);
		
		String type = jsonNode.get("javaType").asText();
		
		Class<?> cls = Class.forName(type);
		
		Object o = objectMapper.convertValue(jsonNode, cls);
		
		System.out.println(o.getClass());
	}

}
