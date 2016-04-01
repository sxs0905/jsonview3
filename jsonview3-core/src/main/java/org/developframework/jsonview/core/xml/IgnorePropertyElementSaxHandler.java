package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.ContainerElement;
import org.xml.sax.Attributes;

class IgnorePropertyElementSaxHandler implements ElementSaxHandler {

	@Override
	public String qName() {
		return "ignore-property";
	}

	@Override
	public void handleAtStartElement(ParserContext context, Attributes attributes) {
		final String data = attributes.getValue("data").trim();
		((ContainerElement) context.getStack().peek()).addIgnoreProperty(data);
	}

	@Override
	public void handleAtEndElement(ParserContext context) {
	}

}
