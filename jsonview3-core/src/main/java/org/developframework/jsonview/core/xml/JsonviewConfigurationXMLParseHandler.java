package org.developframework.jsonview.core.xml;

import java.util.LinkedList;
import java.util.List;

import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * XML配置解析句柄（核心） SAX解析方式
 * 
 * @author qiuzhenhao
 *
 */
class JsonviewConfigurationXMLParseHandler extends DefaultHandler {

	// 解析器组
	private List<ElementSaxParser> elementSaxParsers;
	// 解析器上下文
	private ParserContext context;

	public JsonviewConfigurationXMLParseHandler(JsonviewConfiguration configuration) {
		this.elementSaxParsers = new LinkedList<>();
		this.context = new ParserContext(configuration);
		init();
	}

	/**
	 * 初始化
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
		registerElementSaxParser(new JsonviewPackageElementSaxParser());
	}

	/**
	 * 注册解析器
	 * 
	 * @param parser
	 */
	private void registerElementSaxParser(ElementSaxParser parser) {
		elementSaxParsers.add(parser);
	}

	/**
	 * xml文档开始事件
	 */
	@Override
	public void startDocument() throws SAXException {
		context.getStack().clear();
	}

	/**
	 * xml节点开始事件
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
	 * xml节点关闭事件
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
