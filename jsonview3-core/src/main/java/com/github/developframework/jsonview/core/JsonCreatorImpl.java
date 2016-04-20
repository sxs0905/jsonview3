package com.github.developframework.jsonview.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.developframework.jsonview.core.element.Jsonview;
import com.github.developframework.jsonview.core.element.JsonviewConfiguration;
import com.github.developframework.jsonview.core.processor.Context;
import com.github.developframework.jsonview.core.processor.JsonviewProcessor;
import com.github.developframework.jsonview.data.DataModel;

/**
 * json creator implemention
 * 
 * @author qiuzhenhao
 *
 */
class JsonCreatorImpl implements JsonCreator {

	private JsonviewConfiguration jsonviewConfiguration;
	private ObjectMapper objectMapper;

	protected JsonCreatorImpl(JsonviewConfiguration jsonviewConfiguration, ObjectMapper objectMapper) {
		this.jsonviewConfiguration = jsonviewConfiguration;
		this.objectMapper = objectMapper;
	}

	private ObjectNode constructJson(DataModel dataModel, String namespace, String id) {
		Jsonview jsonview = this.jsonviewConfiguration.extractJsonview(namespace, id);
		ObjectNode root = objectMapper.createObjectNode();
		Context context = new Context();
		context.setDataModel(dataModel);
		context.setObjectMapper(objectMapper);
		context.setJsonviewConfiguration(jsonviewConfiguration);
		JsonviewProcessor processor = new JsonviewProcessor(context, jsonview);
		processor.setNode(root);
		processor.process(null);
		return root;
	}

	@Override
	public String createJson(DataModel dataModel, String namespace, String id) {
		return createJson(dataModel, namespace, id, false);
	}

	@Override
	public String createJson(DataModel dataModel, String namespace, String id, boolean isPretty) {
		ObjectNode root = constructJson(dataModel, namespace, id);
		try {
			if (isPretty) {
				return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
			} else {
				return objectMapper.writeValueAsString(root);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void printJson(JsonGenerator generator, DataModel dataModel, String namespace, String id) {
		ObjectNode root = constructJson(dataModel, namespace, id);
		try {
			objectMapper.writeValue(generator, root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

}
