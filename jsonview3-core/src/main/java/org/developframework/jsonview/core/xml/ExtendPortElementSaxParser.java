package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.core.element.ExtendPortElement;
import org.xml.sax.Attributes;

/**
 * 继承端口节点解析器
 * 
 * @author qiuzhenhao
 * @since 3.1.0
 */
public class ExtendPortElementSaxParser implements ElementSaxParser {

	@Override
	public String qName() {
		return "extend-port";
	}

	/**
	 * 实现：处理SAX节点开始时的操作
	 */
	@Override
	public void handleAtStartElement(ParserContext context, Attributes attributes) {
		final String portName = attributes.getValue("port-name");
		final ExtendPortElement extendPortElement = new ExtendPortElement(portName);
		((ContainerElement) context.getStack().peek()).addChildElement(extendPortElement);
	}

	/**
	 * 实现：处理SAX节点关闭时的操作
	 */
	@Override
	public void handleAtEndElement(ParserContext context) {
		// no opration
	}

}
