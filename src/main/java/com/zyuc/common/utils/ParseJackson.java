package com.zyuc.common.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;


public class ParseJackson {
	private static Log log = LogFactory.getLog(ParseJackson.class);
	public static <T> T parseStringToObject(String jackson, Class<T> clazz) {
		if(jackson!=null && !"".equals(jackson)){
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.getDeserializationConfig().set(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				return objectMapper.readValue(jackson, clazz);
			} catch (Exception e) {
				log.error("parse jackson to object failed! The jackson Str is: \"" + jackson + "\"", e);
				throw new RuntimeException("parse jackson to object failed!", e);
			}
		}
		return null;
	}
	public static Map<String, List<String>> paeseStringToMap(String strJson){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, List<String>> map = mapper.readValue(strJson, Map.class);
			return map;
		} catch (Exception e) {
			log.error("parse jackson to map failed! The jackson Str is: \"" + strJson + "\"", e);
			throw new RuntimeException("parse jackson to map failed!", e);
		}
	}
	public static String parseObjectToLightString(Object obj) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.getSerializationConfig().set(org.codehaus.jackson.map.SerializationConfig.Feature.WRITE_NULL_PROPERTIES, false);
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException("parse jackson to object failed!", e);
		}
	}

}