package org.developframework.jsonview.core.xml;

import java.util.Stack;

import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.element.JsonviewPackage;
import org.xml.sax.Attributes;

class IgnorePropertyElementSaxHandler implements ElementSaxHandler {

	@Override
	public String qName() {
		return "ignore-property";
	}

	@Override
	public JsonviewPackage handleAtStartElement(JsonviewConfiguration configuration, JsonviewPackage jsonviewPackage, Stack<Element> stack, Attributes attributes) {
		final String data = attributes.getValue("data").trim();
		((ContainerElement) stack.peek()).addIgnoreProperty(data);
		return jsonviewPackage;
	}

	@Override
	public JsonviewPackage handleAtEndElement(JsonviewConfiguration configuration, JsonviewPackage jsonviewPackage, Stack<Element> stack) {
		return jsonviewPackage;
	}

}
