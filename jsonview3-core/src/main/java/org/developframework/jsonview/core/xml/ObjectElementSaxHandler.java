package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.ObjectElement;
import org.xml.sax.Attributes;

/**
 * 对象节点解析器
 * 
 * @author qiuzhenhao
 *
 */
class ObjectElementSaxHandler extends ContainerElementSaxHandler<ObjectElement> {

	@Override
	public String qName() {
		return "object";
	}

	/**
	 * 实现： 获取节点实例
	 */
	@Override
	protected ObjectElement getElementInstance(String data, String alias) {
		return new ObjectElement(data, alias);
	}

	/**
	 * 实现：增加其它属性处理
	 */
	@Override
	protected void addOtherAttributes(ObjectElement element, Attributes attributes) {
		element.setNullHidden(attributes.getValue("null-hidden"));
		forClass(element, attributes);
	}

}
