package org.developframework.jsonview.core;

import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.xml.JsonviewConfigurationSaxReader;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * jsonview工厂
 * 
 * @author qiuzhenhao
 *
 */
public class JsonviewFactory {

	private JsonviewConfiguration jsonviewConfiguration;
	private ObjectMapper objectMapper;

	public JsonviewFactory(String... configs) {
		JsonviewConfigurationSaxReader reader = new JsonviewConfigurationSaxReader(configs);
		jsonviewConfiguration = reader.readConfiguration();
		this.objectMapper = new ObjectMapper();
	}

	public JsonviewFactory(JsonviewConfiguration jsonviewConfiguration) {
		this.jsonviewConfiguration = jsonviewConfiguration;
		this.objectMapper = new ObjectMapper();
	}

	/**
	 * 创建一个Json生成器，用默认的objectMapper对象
	 * 
	 * @return
	 */
	public JsonCreator getJsonCreator() {
		return new JsonCreator(jsonviewConfiguration, objectMapper);
	}

	/**
	 * 创建一个Json生成器，用传入的objectMapper对象
	 * 
	 * @param objectMapper
	 * @return
	 */
	public JsonCreator getJsonCreator(ObjectMapper objectMapper) {
		return new JsonCreator(jsonviewConfiguration, objectMapper);
	}

	public JsonviewConfiguration getJsonviewConfiguration() {
		return jsonviewConfiguration;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
}
