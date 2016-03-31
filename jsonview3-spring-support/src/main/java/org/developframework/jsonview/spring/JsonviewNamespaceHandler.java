package org.developframework.jsonview.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class JsonviewNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("scan", new JsonviewScanBeanDefinitionParser());
	}

}
