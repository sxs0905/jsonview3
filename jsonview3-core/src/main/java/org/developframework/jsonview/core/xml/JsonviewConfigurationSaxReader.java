package org.developframework.jsonview.core.xml;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.exception.JsonviewParseXmlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置解析读取器
 * 
 * @author qiuzhenhao
 *
 */
public class JsonviewConfigurationSaxReader {

	private static final Logger logger = LoggerFactory.getLogger(JsonviewConfigurationSaxReader.class);
	// 配置源数组
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

	/**
	 * 读取配置
	 * 
	 * @return
	 */
	public JsonviewConfiguration readConfiguration() {
		JsonviewConfiguration jsonviewConfiguration = new JsonviewConfiguration();
		JsonviewConfigurationXMLParseHandler handler = new JsonviewConfigurationXMLParseHandler(jsonviewConfiguration);
		for (ConfigurationSource source : sources) {
			// 读单个配置源
			readOneFile(handler, source);
			logger.info("Jsonview framework load the configuration file \"{0}\" is success.", source.getSourceName());
		}
		return jsonviewConfiguration;
	}

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

	public ConfigurationSource[] getSources() {
		return sources;
	}

}
