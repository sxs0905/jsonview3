package org.developframework.jsonview.core.xml;

import java.util.Stack;

import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class JsonviewConfigurationXMLParseHandler extends DefaultHandler {

	private JsonviewConfiguration configuration;
	private final ElementSaxHandler[] elementSaxHandlers;
	private ParserContext context;

	public JsonviewConfigurationXMLParseHandler(JsonviewConfiguration configuration, ElementSaxHandler[] elementSaxHandlers) {
		this.configuration = configuration;
		this.elementSaxHandlers = elementSaxHandlers;
		this.context = new ParserContext(configuration);
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		context.setStack(new Stack<>());
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		for (ElementSaxHandler elementSaxHandler : elementSaxHandlers) {
			if (elementSaxHandler.qName().equals(qName)) {
				elementSaxHandler.handleAtStartElement(context, attributes);
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		for (ElementSaxHandler elementSaxHandler : elementSaxHandlers) {
			if (elementSaxHandler.qName().equals(qName)) {
				elementSaxHandler.handleAtEndElement(context);
			}
		}
	}

	public JsonviewConfiguration getConfiguration() {
		return configuration;
	}

}
