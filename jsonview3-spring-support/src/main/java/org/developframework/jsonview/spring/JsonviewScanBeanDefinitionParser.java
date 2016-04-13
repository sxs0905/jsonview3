package org.developframework.jsonview.spring;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.developframework.jsonview.core.JsonviewFactory;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.xml.ConfigurationSource;
import org.developframework.jsonview.core.xml.JsonviewConfigurationSaxReader;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class JsonviewScanBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class<?> getBeanClass(Element element) {
		return JsonviewFactory.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		String locations = element.getAttribute("locations");
		if (StringUtils.hasText(locations)) {
			JsonviewConfiguration jsonviewConfiguration = createJsonviewConfiguration(locations);
			builder.addConstructorArgValue(jsonviewConfiguration);
		}
	}

	private JsonviewConfiguration createJsonviewConfiguration(String locations) {
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
}
