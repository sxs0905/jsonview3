package org.developframework.jsonview.core.xml;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.core.element.ImportElement;
import org.xml.sax.Attributes;

class ImportElementSaxHandler implements ElementSaxHandler {

	@Override
	public String qName() {
		return "import";
	}

	@Override
	public void handleAtStartElement(ParserContext context, Attributes attributes) {
		final String id = attributes.getValue("id").trim();
		String namespace = attributes.getValue("namespace");
		namespace = StringUtils.isNotBlank(namespace) ? namespace.trim() : context.getJsonviewPackage().getNamespace();
		final ImportElement importElement = new ImportElement(namespace, id);
		((ContainerElement) context.getStack().peek()).addElement(importElement);
	}

	@Override
	public void handleAtEndElement(ParserContext context) {
	}

}
