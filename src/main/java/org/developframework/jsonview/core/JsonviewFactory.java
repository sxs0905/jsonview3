package org.developframework.jsonview.core;

import org.developframework.jsonview.core.element.JsonviewConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonviewFactory {

	private JsonviewConfiguration jsonviewConfiguration;
	private ObjectMapper objectMapper;

	public JsonviewFactory(JsonviewConfiguration jsonviewConfiguration) {
		this.jsonviewConfiguration = jsonviewConfiguration;
		this.objectMapper = new ObjectMapper();
	}

	public JsonCreator getJsonCreator() {
		return new JsonCreator(jsonviewConfiguration, objectMapper);
	}

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
