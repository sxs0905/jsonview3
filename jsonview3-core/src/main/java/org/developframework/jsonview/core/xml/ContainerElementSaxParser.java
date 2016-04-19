package org.developframework.jsonview.core.xml;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.exception.JsonviewParseXmlException;
import org.xml.sax.Attributes;

/**
 * A parser for container xml element
 * 
 * @author qiuzhenhao
 *
 * @param <T> container element class
 */
abstract class ContainerElementSaxParser<T extends ContainerElement> extends DescribeContentElementSaxParser<T> {

	/**
	 * Handle for attribute: for-class
	 * 
	 * @param element container element
	 * @param attributes sax attributes
	 */
	protected final void forClass(ContainerElement element, Attributes attributes) {
		final String className = attributes.getValue("for-class");
		if (StringUtils.isNotBlank(className)) {
			try {
				element.setClazz(Class.forName(className));
			} catch (ClassNotFoundException e) {
				throw new JsonviewParseXmlException(String.format("Class \"%s\" is not found, please check configuration file.", className));
			}
		}
	}

	@Override
	public void handleAtEndElement(ParserContext context) {
		((ContainerElement) context.getStack().pop()).loadClassProperty();
	}

	@Override
	protected void otherOperation(ParserContext context, T element) {
		context.getStack().push(element);
	}
}
