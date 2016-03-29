package org.developframework.jsonview.core.xml;

import java.util.Stack;

import org.developframework.jsonview.core.element.ArrayElement;
import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.element.JsonviewPackage;
import org.xml.sax.Attributes;

class ArrayElementSaxHandler extends ContainerElementSaxHandler implements ElementSaxHandler {

	@Override
	public String qName() {
		return "array";
	}

	@Override
	public JsonviewPackage handleAtStartElement(JsonviewConfiguration configuration, JsonviewPackage jsonviewPackage, Stack<Element> stack, Attributes attributes) {
		final String data = attributes.getValue("data").trim();
		final String alias = attributes.getValue("alias");
		final ArrayElement arrayElement = new ArrayElement(data, alias);
		final String className = attributes.getValue("for-class");
		forClass(arrayElement, className);
		((ContainerElement) stack.peek()).addElement(arrayElement);
		stack.push(arrayElement);
		return jsonviewPackage;
	}

	@Override
	public JsonviewPackage handleAtEndElement(JsonviewConfiguration configuration, JsonviewPackage jsonviewPackage, Stack<Element> stack) {
		((ContainerElement) stack.pop()).loadClassProperty();
		return jsonviewPackage;
	}

}
