package org.developframework.jsonview.core.xml;

import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.ImportElement;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.element.JsonviewPackage;
import org.xml.sax.Attributes;

class ImportElementSaxHandler implements ElementSaxHandler {

	@Override
	public String qName() {
		return "import";
	}

	@Override
	public JsonviewPackage handleAtStartElement(JsonviewConfiguration configuration, JsonviewPackage jsonviewPackage, Stack<Element> stack, Attributes attributes) {
		final String id = attributes.getValue("id").trim();
		String namespace = attributes.getValue("namespace");
		namespace = StringUtils.isNotBlank(namespace) ? namespace.trim() : jsonviewPackage.getNamespace();
		final ImportElement importElement = new ImportElement(namespace, id);
		((ContainerElement) stack.peek()).addElement(importElement);
		return jsonviewPackage;
	}

	@Override
	public JsonviewPackage handleAtEndElement(JsonviewConfiguration configuration, JsonviewPackage jsonviewPackage, Stack<Element> stack) {
		return jsonviewPackage;
	}

}
