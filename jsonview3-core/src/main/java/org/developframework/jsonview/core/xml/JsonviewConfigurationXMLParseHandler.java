package org.developframework.jsonview.core.xml;

import java.util.LinkedList;
import java.util.List;

import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class JsonviewConfigurationXMLParseHandler extends DefaultHandler {

	private List<ElementSaxHandler> elementSaxHandlers;
	private ParserContext context;

	public JsonviewConfigurationXMLParseHandler(JsonviewConfiguration configuration) {
		this.elementSaxHandlers = new LinkedList<>();
		this.context = new ParserContext(configuration);
		init();
	}

	private void init() {
		registerElementSaxHandler(new PropertyElementSaxHandler());
		registerElementSaxHandler(new DatePropertyElementSaxHandler());
		registerElementSaxHandler(new IgnorePropertyElementSaxHandler());
		registerElementSaxHandler(new ObjectElementSaxHandler());
		registerElementSaxHandler(new ArrayElementSaxHandler());
		registerElementSaxHandler(new JsonviewElementSaxHandler());
		registerElementSaxHandler(new ImportElementSaxHandler());
		registerElementSaxHandler(new JsonviewPackageElementSaxHandler());
	}

	private void registerElementSaxHandler(ElementSaxHandler handler) {
		elementSaxHandlers.add(handler);
	}

	@Override
	public void startDocument() throws SAXException {
		context.getStack().clear();
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
}
