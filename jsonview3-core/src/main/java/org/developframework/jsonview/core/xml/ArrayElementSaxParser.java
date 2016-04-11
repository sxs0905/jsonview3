package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.ArrayElement;
import org.xml.sax.Attributes;

/**
 * 数组节点解析器
 * 
 * @author qiuzhenhao
 *
 */
class ArrayElementSaxParser extends ContainerElementSaxParser<ArrayElement> {

	@Override
	public String qName() {
		return "array";
	}

	/**
	 * 实现： 获取节点实例
	 */
	@Override
	protected ArrayElement getElementInstance(String data, String alias) {
		return new ArrayElement(data, alias);
	}

	/**
	 * 实现：增加其它属性处理
	 */
	@Override
	protected void addOtherAttributes(ArrayElement element, Attributes attributes) {
		element.setNullHidden(attributes.getValue("null-hidden"));
		forClass(element, attributes);
	}

}
