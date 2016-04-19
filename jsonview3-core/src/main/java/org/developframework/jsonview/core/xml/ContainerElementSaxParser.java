package org.developframework.jsonview.core.xml;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.exception.JsonviewParseXmlException;
import org.xml.sax.Attributes;

/**
 * 容器节点解析器
 * 
 * @author qiuzhenhao
 *
 * @param <T> 容器节点类型
 */
abstract class ContainerElementSaxParser<T extends ContainerElement> extends DescribeContentElementSaxParser<T> {

	/**
	 * 添加forclass处理
	 * 
	 * @param element 容器节点
	 * @param attributes 属性集
	 */
	protected final void forClass(ContainerElement element, Attributes attributes) {
		final String className = attributes.getValue("for-class");
		if (StringUtils.isNotBlank(className)) {
			try {
				element.setClazz(Class.forName(className));
			} catch (ClassNotFoundException e) {
				throw new JsonviewParseXmlException(String.format("Class \"%s\" is not found, please check configuration file.", className));
			}
		}
	}

	/**
	 * 实现：处理SAX节点关闭时的操作
	 */
	@Override
	public void handleAtEndElement(ParserContext context) {
		((ContainerElement) context.getStack().pop()).loadClassProperty();
	}

	/**
	 * 实现：其它操作
	 */
	@Override
	protected void otherOperation(ParserContext context, T element) {
		context.getStack().push(element);
	}
}
