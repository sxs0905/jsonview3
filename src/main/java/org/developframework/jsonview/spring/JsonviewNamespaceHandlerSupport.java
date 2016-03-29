package org.developframework.jsonview.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class JsonviewNamespaceHandlerSupport extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("scanner", new JsonviewScanBeanDefinitionParser());
	}

}
