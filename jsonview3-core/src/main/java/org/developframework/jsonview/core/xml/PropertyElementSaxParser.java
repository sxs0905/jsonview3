package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.NormalPropertyElement;
import org.developframework.jsonview.core.element.PropertyElement;
import org.xml.sax.Attributes;

/**
 * 属性节点解析器
 * 
 * @author qiuzhenhao
 *
 */
class PropertyElementSaxParser extends DescribeContentElementSaxParser<PropertyElement> {

	@Override
	public String qName() {
		return "property";
	}

	/**
	 * 实现： 获取节点实例
	 */
	@Override
	protected PropertyElement getElementInstance(String data, String alias) {
		return new NormalPropertyElement(data, alias);
	}

	/**
	 * 实现：增加其它属性处理
	 */
	@Override
	protected void addOtherAttributes(PropertyElement element, Attributes attributes) {
		element.setNullHidden(attributes.getValue("null-hidden"));
		element.setConvertor(attributes.getValue("convertor"));
	}

	/**
	 * 实现：其它处理
	 */
	@Override
	protected void otherOperation(ParserContext context, PropertyElement element) {
		// no operation
	}

}
