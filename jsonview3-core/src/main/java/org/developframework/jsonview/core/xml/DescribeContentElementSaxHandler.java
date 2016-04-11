package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.core.element.Element;
import org.xml.sax.Attributes;

/**
 * 描述内容型节点解析器
 * 
 * @author Administrator
 *
 * @param <T>
 */
abstract class DescribeContentElementSaxHandler<T extends Element> implements ElementSaxHandler {

	/**
	 * 实现：处理SAX节点开始时的操作
	 */
	@Override
	public void handleAtStartElement(ParserContext context, Attributes attributes) {
		final String data = attributes.getValue("data").trim();
		final String alias = attributes.getValue("alias");
		// 扩展点 获取节点实例
		T element = getElementInstance(data, alias);
		// 扩展点 增加其它属性处理
		addOtherAttributes(element, attributes);
		((ContainerElement) context.getStack().peek()).addChildElement(element);
		// 扩展点 其它操作
		otherOperation(context, element);
	}

	/**
	 * 实现：处理SAX节点关闭时的操作
	 */
	@Override
	public void handleAtEndElement(ParserContext context) {
		// no operation
	}

	/**
	 * 扩展： 获取节点实例
	 * 
	 * @param data
	 * @param alias
	 * @return
	 */
	protected abstract T getElementInstance(String data, String alias);

	/**
	 * 扩展： 增加其它属性处理
	 * 
	 * @param element
	 * @param attributes
	 */
	protected abstract void addOtherAttributes(T element, Attributes attributes);

	/**
	 * 扩展： 其它操作
	 * 
	 * @param context
	 * @param element
	 */
	protected abstract void otherOperation(ParserContext context, T element);
}
