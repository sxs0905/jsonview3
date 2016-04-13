package org.developframework.jsonview.core;

import org.developframework.jsonview.core.element.Jsonview;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.JsonviewProcessor;
import org.developframework.jsonview.data.DataModel;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * json生成器
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

	/**
	 * 构造json树结构
	 * 
	 * @param dataModel
	 * @param namespace
	 * @param id
	 * @return
	 */
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

	/**
	 * 创建json字符串
	 * 
	 * @param dataModel
	 * @param namespace
	 * @param id
	 * @return
	 */
	@Override
	public String createJson(DataModel dataModel, String namespace, String id) {
		return createJson(dataModel, namespace, id, false);
	}

	/**
	 * 创建json字符串，isPretty=true时美化json
	 * 
	 * @param dataModel
	 * @param namespace
	 * @param id
	 * @param isPretty
	 * @return
	 */
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

	/**
	 * 向JsonGenerator输出json
	 * 
	 * @param generator
	 * @param dataModel
	 * @param namespace
	 * @param id
	 */
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
