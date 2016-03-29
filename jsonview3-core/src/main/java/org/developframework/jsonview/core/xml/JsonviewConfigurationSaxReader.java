package org.developframework.jsonview.core.xml;

import java.io.InputStream;
import java.util.Objects;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.exception.ConfigurationFileNotFoundException;
import org.developframework.jsonview.exception.JsonviewParseXmlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonviewConfigurationSaxReader {

	private static final Logger logger = LoggerFactory.getLogger(JsonviewConfigurationSaxReader.class);
	private String[] configs;

	public JsonviewConfigurationSaxReader(String[] configs) {
		this.configs = configs;
	}

	public JsonviewConfiguration readConfiguration() {
		JsonviewConfiguration jsonviewConfiguration = new JsonviewConfiguration();
		ElementSaxHandler[] elementSaxHandlers = new ElementSaxHandler[]{
				new PropertyElementSaxHandler(), new DatePropertyElementSaxHandler(), new IgnorePropertyElementSaxHandler(), new ObjectElementSaxHandler(), new ArrayElementSaxHandler(), new JsonviewElementSaxHandler(), new ImportElementSaxHandler(), new JsonviewPackageElementSaxHandler(),
		};
		for (String config : configs) {
			InputStream is = this.getClass().getResourceAsStream(config);
			if (Objects.isNull(is)) {
				throw new ConfigurationFileNotFoundException("Jsonview framework load the configuration is failed, because not found: " + config);
			} else {
				logger.info(String.format("Jsonview framework load the configuration file \"%s\" is success.", config));
			}
			try {
				readOneFile(jsonviewConfiguration, is, elementSaxHandlers);
				is.close();
			} catch (Exception e) {
				throw new JsonviewParseXmlException("Jsonview framework parse XML Error for configuration file: " + config, e);
			}
		}
		elementSaxHandlers = null;
		return jsonviewConfiguration;
	}

	private void readOneFile(JsonviewConfiguration jsonviewConfiguration, InputStream is, ElementSaxHandler[] elementSaxHandlers) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		JsonviewConfigurationXMLParseHandler handler = new JsonviewConfigurationXMLParseHandler(jsonviewConfiguration, elementSaxHandlers);
		saxParser.parse(is, handler);
		is.close();
	}

}
