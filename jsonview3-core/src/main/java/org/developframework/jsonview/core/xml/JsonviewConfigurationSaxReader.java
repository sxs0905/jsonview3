package org.developframework.jsonview.core.xml;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.exception.JsonviewParseXmlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonviewConfigurationSaxReader {

	private static final Logger logger = LoggerFactory.getLogger(JsonviewConfigurationSaxReader.class);
	private ConfigurationSource[] sources;

	public JsonviewConfigurationSaxReader(String config) {
		this.sources = new ConfigurationSource[]{
				new FileConfigurationSource(config)
		};
	}

	public JsonviewConfigurationSaxReader(String[] configs) {
		this.sources = new ConfigurationSource[configs.length];
		for (int i = 0; i < configs.length; i++) {
			sources[i] = new FileConfigurationSource(configs[i]);
		}
	}

	public JsonviewConfigurationSaxReader(ConfigurationSource source) {
		this.sources = new ConfigurationSource[]{
				source
		};
	}

	public JsonviewConfigurationSaxReader(ConfigurationSource[] sources) {
		this.sources = sources;
	}

	public JsonviewConfiguration readConfiguration() {
		JsonviewConfiguration jsonviewConfiguration = new JsonviewConfiguration();
		ElementSaxHandler[] elementSaxHandlers = new ElementSaxHandler[]{
				new PropertyElementSaxHandler(), new DatePropertyElementSaxHandler(), new IgnorePropertyElementSaxHandler(), new ObjectElementSaxHandler(), new ArrayElementSaxHandler(), new JsonviewElementSaxHandler(), new ImportElementSaxHandler(), new JsonviewPackageElementSaxHandler(),
		};
		for (ConfigurationSource source : sources) {
			readOneFile(jsonviewConfiguration, source, elementSaxHandlers);
			logger.info("Jsonview framework load the configuration file \"{0}\" is success.", source.getSourceName());
		}
		elementSaxHandlers = null;
		return jsonviewConfiguration;
	}

	private void readOneFile(JsonviewConfiguration jsonviewConfiguration, ConfigurationSource source, ElementSaxHandler[] elementSaxHandlers) {
		try {
			InputStream is = source.getInputStream();
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			JsonviewConfigurationXMLParseHandler handler = new JsonviewConfigurationXMLParseHandler(jsonviewConfiguration, elementSaxHandlers);
			saxParser.parse(is, handler);
			is.close();
		} catch (Exception e) {
			throw new JsonviewParseXmlException(source.getSourceName(), e);
		}
	}

	public ConfigurationSource[] getSources() {
		return sources;
	}

}
