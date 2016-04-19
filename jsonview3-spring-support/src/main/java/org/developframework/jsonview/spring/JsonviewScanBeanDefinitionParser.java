package org.developframework.jsonview.spring;

import org.developframework.jsonview.core.JsonviewFactory;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Spring scanning jsonview namespace tag parser
 * 
 * @author qiuzhenhao
 *
 */
public class JsonviewScanBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class<?> getBeanClass(Element element) {
		return JsonviewFactory.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		final String locations = element.getAttribute("locations");
		if (StringUtils.hasText(locations)) {
			JsonviewScanLoader loader = new JsonviewScanLoader(locations);
			JsonviewConfiguration jsonviewConfiguration = loader.createJsonviewConfiguration();
			builder.addConstructorArgValue(jsonviewConfiguration);
		}
	}

}
