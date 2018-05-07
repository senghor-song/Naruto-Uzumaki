package com.ruiec.web.util;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	
private static final ObjectMapper om = new ObjectMapper();
	
	static {
		om.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	}
	
	public static <T> T jsonToObject(String jsonStr, Class<T> clazz){
		T object = null;
		try {
			object = om.readValue(jsonStr, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return object;
	}
	
	public static String objectToString(Object o){
		String jsonStr = null;
		try {
			jsonStr = om.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return jsonStr;
	}
}
