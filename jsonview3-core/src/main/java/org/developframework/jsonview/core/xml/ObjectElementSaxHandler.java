package org.developframework.jsonview.core.xml;

import java.util.Stack;

import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.element.JsonviewPackage;
import org.developframework.jsonview.core.element.ObjectElement;
import org.xml.sax.Attributes;

class ObjectElementSaxHandler extends ContainerElementSaxHandler implements ElementSaxHandler {

	@Override
	public String qName() {
		return "object";
	}

	@Override
	public JsonviewPackage handleAtStartElement(JsonviewConfiguration configuration, JsonviewPackage jsonviewPackage, Stack<Element> stack, Attributes attributes) {
		final String data = attributes.getValue("data").trim();
		final String alias = attributes.getValue("alias");
		final ObjectElement objectElement = new ObjectElement(data, alias);
		final String className = attributes.getValue("for-class");
		forClass(objectElement, className);
		((ContainerElement) stack.peek()).addElement(objectElement);
		stack.push(objectElement);
		return jsonviewPackage;
	}

	@Override
	public JsonviewPackage handleAtEndElement(JsonviewConfiguration configuration, JsonviewPackage jsonviewPackage, Stack<Element> stack) {
		((ContainerElement) stack.pop()).loadClassProperty();
		return jsonviewPackage;
	}

}
