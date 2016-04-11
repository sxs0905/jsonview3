package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.DatePropertyElement;
import org.xml.sax.Attributes;

/**
 * 时间日期属性节点解析器
 * 
 * @author qiuzhenhao
 *
 */
class DatePropertyElementSaxParser extends DescribeContentElementSaxParser<DatePropertyElement> {

	@Override
	public String qName() {
		return "property-date";
	}

	/**
	 * 实现： 获取节点实例
	 */
	@Override
	protected DatePropertyElement getElementInstance(String data, String alias) {
		return new DatePropertyElement(data, alias);
	}

	/**
	 * 实现：增加其它属性处理
	 */
	@Override
	protected void addOtherAttributes(DatePropertyElement element, Attributes attributes) {
		element.setPattern(attributes.getValue("pattern"));
		element.setNullHidden(attributes.getValue("null-hidden"));
		element.setConvertor(attributes.getValue("convertor"));
	}

	/**
	 * 实现：其它处理
	 */
	@Override
	protected void otherOperation(ParserContext context, DatePropertyElement element) {
		// no operation
	}

}
