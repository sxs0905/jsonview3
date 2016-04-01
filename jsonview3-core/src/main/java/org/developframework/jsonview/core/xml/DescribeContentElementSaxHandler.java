package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.core.element.Element;
import org.xml.sax.Attributes;

abstract class DescribeContentElementSaxHandler<T extends Element> implements ElementSaxHandler {

	@Override
	public void handleAtStartElement(ParserContext context, Attributes attributes) {
		final String data = attributes.getValue("data").trim();
		final String alias = attributes.getValue("alias");
		T element = getElementInstance(data, alias);
		addOtherAttributes(element, attributes);
		((ContainerElement) context.getStack().peek()).addElement(element);
		otherOperation(context, element);
	}

	@Override
	public void handleAtEndElement(ParserContext context) {
		// no operation
	}

	protected abstract T getElementInstance(String data, String alias);

	protected abstract void addOtherAttributes(T element, Attributes attributes);

	protected abstract void otherOperation(ParserContext context, T element);
}
