package org.developframework.jsonview.core.xml;

import java.util.Stack;

import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.core.element.DatePropertyElement;
import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.element.JsonviewPackage;
import org.developframework.jsonview.core.element.PropertyElement;
import org.xml.sax.Attributes;

public class DatePropertyElementSaxHandler implements ElementSaxHandler {

	@Override
	public String qName() {
		return "property-date";
	}

	@Override
	public JsonviewPackage handleAtStartElement(JsonviewConfiguration configuration, JsonviewPackage jsonviewPackage, Stack<Element> stack, Attributes attributes) {
		final String data = attributes.getValue("data").trim();
		final String alias = attributes.getValue("alias");
		final String pattern = attributes.getValue("pattern");
		final PropertyElement propertyElement = new DatePropertyElement(data, alias, pattern);
		((ContainerElement) stack.peek()).addElement(propertyElement);
		return jsonviewPackage;
	}

	@Override
	public JsonviewPackage handleAtEndElement(JsonviewConfiguration configuration, JsonviewPackage jsonviewPackage, Stack<Element> stack) {
		return jsonviewPackage;
	}

}
