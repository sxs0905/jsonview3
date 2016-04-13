package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.ContainerElement;
import org.xml.sax.Attributes;

/**
 * 忽略的属性节点解析器
 * 
 * @author qiuzhenhao
 *
 */
class IgnorePropertyElementSaxParser implements ElementSaxParser {

	@Override
	public String qName() {
		return "ignore-property";
	}

	/**
	 * 实现：处理SAX节点开始时的操作
	 */
	@Override
	public void handleAtStartElement(ParserContext context, Attributes attributes) {
		final String name = attributes.getValue("name").trim();
		((ContainerElement) context.getStack().peek()).addIgnoreProperty(name);
	}

	/**
	 * 实现：处理SAX节点关闭时的操作
	 */
	@Override
	public void handleAtEndElement(ParserContext context) {
		// no opration
	}

}
