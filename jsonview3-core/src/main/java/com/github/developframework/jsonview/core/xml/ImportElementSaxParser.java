package com.github.developframework.jsonview.core.xml;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;

import com.github.developframework.jsonview.core.element.ContainerElement;
import com.github.developframework.jsonview.core.element.ImportElement;

/**
 * A parser for xml element: import
 * 
 * @author qiuzhenhao
 *
 */
class ImportElementSaxParser implements ElementSaxParser {

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
		((ContainerElement) context.getStack().peek()).addChildElement(importElement);
	}

	@Override
	public void handleAtEndElement(ParserContext context) {
		// no operation
	}

}
