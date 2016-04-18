package org.developframework.jsonview.core.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.data.DataModel;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 环境上下文
 * 
 * @author qiuzhenhao
 *
 */
public class Context {

	private ObjectMapper objectMapper;
	private DataModel dataModel;
	private JsonviewConfiguration jsonviewConfiguration;
	private Map<String, ExtendPortProcessor.ExtendCallback> extendPortMap = new HashMap<>();

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

	public void pushExtendCallback(String portName, ExtendPortProcessor.ExtendCallback callback) {
		extendPortMap.put(portName, callback);
	}

	public Optional<ExtendPortProcessor.ExtendCallback> getExtendCallback(String port) {
		return Optional.ofNullable(extendPortMap.get(port));
	}

}
