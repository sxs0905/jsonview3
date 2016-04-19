package org.developframework.jsonview.core.xml;

import java.util.ArrayList;
import java.util.List;

import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX Handler
 * 
 * @author qiuzhenhao
 *
 */
class JsonviewConfigurationXMLParseHandler extends DefaultHandler {

	// ElementSaxParser list
	private List<ElementSaxParser> elementSaxParsers;
	// context
	private ParserContext context;

	public JsonviewConfigurationXMLParseHandler(JsonviewConfiguration configuration) {
		this.elementSaxParsers = new ArrayList<>(14);
		this.context = new ParserContext(configuration);
		init();
	}

	/**
	 * initialize for register all ElementSaxParser
	 */
	private void init() {
		registerElementSaxParser(new PropertyElementSaxParser());
		registerElementSaxParser(new DatePropertyElementSaxParser());
		registerElementSaxParser(new BooleanPropertyElementSaxParser());
		registerElementSaxParser(new UnixTimestampPropertyElementSaxParser());
		registerElementSaxParser(new IgnorePropertyElementSaxParser());
		registerElementSaxParser(new ObjectElementSaxParser());
		registerElementSaxParser(new ArrayElementSaxParser());
		registerElementSaxParser(new VirtualObjectElementSaxParser());
		registerElementSaxParser(new MappingObjectElementSaxParser());
		registerElementSaxParser(new LinkObjectElementSaxParser());
		registerElementSaxParser(new JsonviewElementSaxParser());
		registerElementSaxParser(new ImportElementSaxParser());
		registerElementSaxParser(new ExtendPortElementSaxParser());
		registerElementSaxParser(new JsonviewPackageElementSaxParser());
	}

	/**
	 * register parser
	 * 
	 * @param parser parser
	 */
	private void registerElementSaxParser(ElementSaxParser parser) {
		elementSaxParsers.add(parser);
	}

	/**
	 * sax start document
	 */
	@Override
	public void startDocument() throws SAXException {
		context.getStack().clear();
	}

	/**
	 * sax start element
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		for (ElementSaxParser parser : elementSaxParsers) {
			if (parser.qName().equals(qName)) {
				parser.handleAtStartElement(context, attributes);
			}
		}
	}

	/**
	 * sax end element
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		for (ElementSaxParser parser : elementSaxParsers) {
			if (parser.qName().equals(qName)) {
				parser.handleAtEndElement(context);
			}
		}
	}
}
