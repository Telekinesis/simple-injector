package org.telekinesis.simpleinjector.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyMapBuilder {
	private PropertyMapBuilder(){}
	
	public static Map<String, String> build(Properties p){
		Map<String, String> map = new HashMap<String, String>();
		for (String key : p.stringPropertyNames()) 
			map.put(key, p.getProperty(key));
		return map;
	}
}
