package org.developframework.jsonview.spring;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.developframework.jsonview.core.JsonviewFactory;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.xml.ConfigurationSource;
import org.developframework.jsonview.core.xml.JsonviewConfigurationSaxReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * jsonview配置扫描加载器
 * 
 * @author qiuzhenhao
 *
 */
public class JsonviewScanLoader {

	// 扫描地址
	private String locations;

	public JsonviewScanLoader(String locations) {
		this.locations = locations;
	}

	/**
	 * 创建配置
	 * 
	 * @return
	 */
	public JsonviewConfiguration createJsonviewConfiguration() {
		final ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			final Resource[] resources = resolver.getResources(locations);
			final Set<ConfigurationSource> sources = new HashSet<>(resources.length);
			for (int i = 0; i < resources.length; i++) {
				sources.add(new SpringResourceConfigurationSource(resources[i]));
			}
			final JsonviewConfigurationSaxReader reader = new JsonviewConfigurationSaxReader(sources);
			return reader.readConfiguration();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 创建工厂
	 * 
	 * @return
	 */
	public JsonviewFactory createJsonviewFactory() {
		return new JsonviewFactory(createJsonviewConfiguration());
	}

	public String getLocations() {
		return locations;
	}

}
