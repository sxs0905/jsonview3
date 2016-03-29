package org.developframework.jsonview.spring;

import java.io.IOException;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.w3c.dom.Element;

public class JsonviewScanBeanDefinitionParser implements BeanDefinitionParser {

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		String locations = element.getAttribute("locations");
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			Resource[] resources = resolver.getResources(locations);
			for (Resource resource : resources) {
				System.out.println(resource.getFilename());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
