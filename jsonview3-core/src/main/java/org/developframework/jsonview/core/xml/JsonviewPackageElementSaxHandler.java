package org.developframework.jsonview.core.xml;

import java.util.Stack;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.element.JsonviewPackage;
import org.xml.sax.Attributes;

class JsonviewPackageElementSaxHandler implements ElementSaxHandler {

	@Override
	public String qName() {
		return "jsonview-package";
	}

	@Override
	public JsonviewPackage handleAtStartElement(JsonviewConfiguration configuration, JsonviewPackage jsonviewPackage, Stack<Element> stack, Attributes attributes) {
		final String namespace = attributes.getValue("namespace").trim();
		return new JsonviewPackage(namespace);
	}

	@Override
	public JsonviewPackage handleAtEndElement(JsonviewConfiguration configuration, JsonviewPackage jsonviewPackage, Stack<Element> stack) {
		configuration.addJsonviewPackage(jsonviewPackage);
		return jsonviewPackage;
	}

}
