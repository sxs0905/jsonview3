package org.developframework.jsonview.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 注册spring配置项的命名空间
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
