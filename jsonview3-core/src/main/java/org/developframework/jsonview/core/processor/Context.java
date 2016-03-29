package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.data.DataModel;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Context {

	private ObjectMapper objectMapper;
	private DataModel dataModel;
	private JsonviewConfiguration jsonviewConfiguration;

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public DataModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
	}

	public JsonviewConfiguration getJsonviewConfiguration() {
		return jsonviewConfiguration;
	}

	public void setJsonviewConfiguration(JsonviewConfiguration jsonviewConfiguration) {
		this.jsonviewConfiguration = jsonviewConfiguration;
	}

}
