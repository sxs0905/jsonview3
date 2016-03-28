package org.developframework.jsonview.core.xml;

import java.util.Stack;

import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.element.JsonviewPackage;
import org.developframework.jsonview.core.element.NormalPropertyElement;
import org.developframework.jsonview.core.element.PropertyElement;
import org.xml.sax.Attributes;

public class PropertyElementSaxHandler implements ElementSaxHandler {

	@Override
	public String qName() {
		return "property";
	}

	@Override
	public JsonviewPackage handleAtStartElement(JsonviewConfiguration configuration, JsonviewPackage jsonviewPackage, Stack<Element> stack, Attributes attributes) {
		final String data = attributes.getValue("data").trim();
		final String alias = attributes.getValue("alias");
		final PropertyElement propertyElement = new NormalPropertyElement(data, alias);
		((ContainerElement) stack.peek()).addElement(propertyElement);
		return jsonviewPackage;
	}

	@Override
	public JsonviewPackage handleAtEndElement(JsonviewConfiguration configuration, JsonviewPackage jsonviewPackage, Stack<Element> stack) {
		return jsonviewPackage;
	}

}
