package org.developframework.jsonview.core;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.xml.ConfigurationSource;
import org.developframework.jsonview.core.xml.FileConfigurationSource;
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
		Objects.requireNonNull(configs);
		Set<ConfigurationSource> sources = new HashSet<>();
		for (String config : configs) {
			sources.add(new FileConfigurationSource(config));
		}
		JsonviewConfigurationSaxReader reader = new JsonviewConfigurationSaxReader(sources);
		jsonviewConfiguration = reader.readConfiguration();
		this.objectMapper = new ObjectMapper();
	}

	public JsonviewFactory(Set<String> configs) {
		Objects.requireNonNull(configs);
		Set<ConfigurationSource> sources = new HashSet<>();
		for (String config : configs) {
			sources.add(new FileConfigurationSource(config));
		}
		JsonviewConfigurationSaxReader reader = new JsonviewConfigurationSaxReader(sources);
		jsonviewConfiguration = reader.readConfiguration();
		this.objectMapper = new ObjectMapper();
	}

	public JsonviewFactory(JsonviewConfiguration jsonviewConfiguration) {
		Objects.requireNonNull(jsonviewConfiguration);
		this.jsonviewConfiguration = jsonviewConfiguration;
		this.objectMapper = new ObjectMapper();
	}

	/**
	 * 创建一个Json生成器，用默认的objectMapper对象
	 * 
	 * @return JsonCreator实例
	 */
	public JsonCreator getJsonCreator() {
		return new JsonCreatorImpl(jsonviewConfiguration, objectMapper);
	}

	/**
	 * 创建一个Json生成器，用传入的objectMapper对象
	 * 
	 * @param objectMapper 传入的objectMapper对象
	 * @return JsonCreator实例
	 */
	public JsonCreator getJsonCreator(ObjectMapper objectMapper) {
		return new JsonCreatorImpl(jsonviewConfiguration, objectMapper);
	}

	public JsonviewConfiguration getJsonviewConfiguration() {
		return jsonviewConfiguration;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
}
