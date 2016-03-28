package org.developframework.jsonview.core.xml;

import java.util.Stack;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.element.JsonviewPackage;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class JsonviewConfigurationXMLParseHandler extends DefaultHandler {

	private JsonviewConfiguration configuration;
	private JsonviewPackage jsonviewPackage;
	private Stack<Element> stack;
	private final ElementSaxHandler[] elementSaxHandlers;

	public JsonviewConfigurationXMLParseHandler(JsonviewConfiguration configuration, ElementSaxHandler[] elementSaxHandlers) {
		this.configuration = configuration;
		this.elementSaxHandlers = elementSaxHandlers;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		stack = new Stack<>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		for (ElementSaxHandler elementSaxHandler : elementSaxHandlers) {
			if (elementSaxHandler.qName().equals(qName)) {
				jsonviewPackage = elementSaxHandler.handleAtStartElement(configuration, jsonviewPackage, stack, attributes);
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		for (ElementSaxHandler elementSaxHandler : elementSaxHandlers) {
			if (elementSaxHandler.qName().equals(qName)) {
				jsonviewPackage = elementSaxHandler.handleAtEndElement(configuration, jsonviewPackage, stack);
			}
		}
	}

	public JsonviewConfiguration getConfiguration() {
		return configuration;
	}

}
