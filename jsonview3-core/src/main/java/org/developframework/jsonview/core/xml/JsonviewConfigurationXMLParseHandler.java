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
	private List<ElementSaxHandler> elementSaxHandlers;
	// 解析器上下文
	private ParserContext context;

	public JsonviewConfigurationXMLParseHandler(JsonviewConfiguration configuration) {
		this.elementSaxHandlers = new LinkedList<>();
		this.context = new ParserContext(configuration);
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		registerElementSaxHandler(new PropertyElementSaxHandler());
		registerElementSaxHandler(new DatePropertyElementSaxHandler());
		registerElementSaxHandler(new IgnorePropertyElementSaxHandler());
		registerElementSaxHandler(new ObjectElementSaxHandler());
		registerElementSaxHandler(new ArrayElementSaxHandler());
		registerElementSaxHandler(new MappingObjectElementSaxHandler());
		registerElementSaxHandler(new LinkObjectElementSaxHandler());
		registerElementSaxHandler(new JsonviewElementSaxHandler());
		registerElementSaxHandler(new ImportElementSaxHandler());
		registerElementSaxHandler(new JsonviewPackageElementSaxHandler());
	}

	/**
	 * 注册解析器
	 * 
	 * @param handler
	 */
	private void registerElementSaxHandler(ElementSaxHandler handler) {
		elementSaxHandlers.add(handler);
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
		for (ElementSaxHandler elementSaxHandler : elementSaxHandlers) {
			if (elementSaxHandler.qName().equals(qName)) {
				elementSaxHandler.handleAtStartElement(context, attributes);
			}
		}
	}

	/**
	 * xml节点关闭事件
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		for (ElementSaxHandler elementSaxHandler : elementSaxHandlers) {
			if (elementSaxHandler.qName().equals(qName)) {
				elementSaxHandler.handleAtEndElement(context);
			}
		}
	}
}
