package com.github.developframework.jsonview.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * To register the spring configuration namespace
 * 
 * @author qiuzhenhao
 *
 */
public class JsonviewNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("scan", new JsonviewScanBeanDefinitionParser());
	}

}
