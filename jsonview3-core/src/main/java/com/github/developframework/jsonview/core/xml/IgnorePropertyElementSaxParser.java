package com.github.developframework.jsonview.core.xml;

import org.xml.sax.Attributes;

import com.github.developframework.jsonview.core.element.ContainerElement;

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
