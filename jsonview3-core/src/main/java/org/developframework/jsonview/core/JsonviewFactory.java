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
 * jsonview factory
 * 
 * @author qiuzhenhao
 *
 */
public class JsonviewFactory {

	private JsonviewConfiguration jsonviewConfiguration;
	private ObjectMapper objectMapper;

	/**
	 * constructor for string array
	 * 
	 * @param configs configuration filename array
	 */
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

	/**
	 * constructor for string set
	 * 
	 * @param configs configuration filename set
	 */
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

	/**
	 * constructor for jsonviewConfiguration
	 * 
	 * @param jsonviewConfiguration jsonviewConfiguration
	 */
	public JsonviewFactory(JsonviewConfiguration jsonviewConfiguration) {
		Objects.requireNonNull(jsonviewConfiguration);
		this.jsonviewConfiguration = jsonviewConfiguration;
		this.objectMapper = new ObjectMapper();
	}

	/**
	 * Create a json creator, use the default objectMapper object
	 * 
	 * @return JsonCreator instance
	 */
	public JsonCreator getJsonCreator() {
		return new JsonCreatorImpl(jsonviewConfiguration, objectMapper);
	}

	/**
	 * Create a json creator, with the incoming objectMapper object
	 * 
	 * @param objectMapper incoming objectMapper object
	 * @return JsonCreator instance
	 */
	public JsonCreator getJsonCreator(ObjectMapper objectMapper) {
		return new JsonCreatorImpl(jsonviewConfiguration, objectMapper);
	}

	/**
	 * get jsonviewConfiguration
	 * 
	 * @return jsonviewConfiguration
	 */
	public JsonviewConfiguration getJsonviewConfiguration() {
		return jsonviewConfiguration;
	}

	/**
	 * get objectMapper
	 * 
	 * @return objectMapper
	 */
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
}
