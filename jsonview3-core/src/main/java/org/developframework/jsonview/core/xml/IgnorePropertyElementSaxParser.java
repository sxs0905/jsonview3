package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.ContainerElement;
import org.xml.sax.Attributes;

/**
 * A parser for xml element: ignore-property
 * 
 * @author qiuzhenhao
 *
 */
class IgnorePropertyElementSaxParser implements ElementSaxParser {

	@Override
	public String qName() {
		return "ignore-property";
	}

	@Override
	public void handleAtStartElement(ParserContext context, Attributes attributes) {
		final String name = attributes.getValue("name").trim();
		((ContainerElement) context.getStack().peek()).addIgnoreProperty(name);
	}

	@Override
	public void handleAtEndElement(ParserContext context) {
		// no operation
	}

}
