package org.developframework.jsonview.core.xml;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.exception.JsonviewParseXmlException;

public abstract class ContainerElementSaxHandler {

	protected void forClass(ContainerElement element, String className) {
		if (StringUtils.isNotBlank(className)) {
			try {
				element.setClazz(Class.forName(className));
			} catch (ClassNotFoundException e) {
				throw new JsonviewParseXmlException(String.format("Class \"%s\" is not found, please check configuration file.", className));
			}
		}
	}
}
