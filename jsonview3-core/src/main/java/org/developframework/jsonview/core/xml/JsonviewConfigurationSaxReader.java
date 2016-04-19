package org.developframework.jsonview.core.xml;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.exception.JsonviewParseXmlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The jsonview configuration reader
 * 
 * @author qiuzhenhao
 *
 */
public class JsonviewConfigurationSaxReader {

	private static final Logger logger = LoggerFactory.getLogger(JsonviewConfigurationSaxReader.class);
	// source set
	private Set<ConfigurationSource> sources;

	public JsonviewConfigurationSaxReader(String config) {
		this.sources = new HashSet<>(1);
		sources.add(new FileConfigurationSource(config));
	}

	public JsonviewConfigurationSaxReader(ConfigurationSource source) {
		this.sources = new HashSet<>(1);
		sources.add(source);
	}

	public JsonviewConfigurationSaxReader(Set<ConfigurationSource> sources) {
		this.sources = sources;
	}

	/**
	 * read configuration
	 * 
	 * @return jsonviewConfiguration
	 */
	public JsonviewConfiguration readConfiguration() {
		JsonviewConfiguration jsonviewConfiguration = new JsonviewConfiguration();
		JsonviewConfigurationXMLParseHandler handler = new JsonviewConfigurationXMLParseHandler(jsonviewConfiguration);
		for (ConfigurationSource source : sources) {
			// read sinple configuration file
			readOneFile(handler, source);
			logger.info("Jsonview framework loaded the configuration file \"{}\".", source.getSourceName());
		}
		return jsonviewConfiguration;
	}

	/**
	 * read sinple configuration file
	 * 
	 * @param handler SAX handler
	 * @param source configurationSource
	 */
	private void readOneFile(JsonviewConfigurationXMLParseHandler handler, ConfigurationSource source) {
		try {
			InputStream is = source.getInputStream();
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(is, handler);
			is.close();
		} catch (Exception e) {
			throw new JsonviewParseXmlException(source.getSourceName(), e);
		}
	}

	public Set<ConfigurationSource> getSources() {
		return sources;
	}

}
