package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.ContainerElement;
import org.xml.sax.Attributes;

/**
 * 忽略的属性节点解析器
 * 
 * @author qiuzhenhao
 *
 */
class IgnorePropertyElementSaxHandler implements ElementSaxHandler {

	@Override
	public String qName() {
		return "ignore-property";
	}

	/**
	 * 实现：处理SAX节点开始时的操作
	 */
	@Override
	public void handleAtStartElement(ParserContext context, Attributes attributes) {
		final String data = attributes.getValue("data").trim();
		((ContainerElement) context.getStack().peek()).addIgnoreProperty(data);
	}

	/**
	 * 实现：处理SAX节点关闭时的操作
	 */
	@Override
	public void handleAtEndElement(ParserContext context) {
		// no opration
	}

}
