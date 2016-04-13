package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.UnixTimestampPropertyElement;
import org.xml.sax.Attributes;

/**
 * Unix时间戳属性节点解析器
 * 
 * @author qiuzhenhao
 *
 */
class UnixTimestampPropertyElementSaxParser extends DescribeContentElementSaxParser<UnixTimestampPropertyElement> {

	@Override
	public String qName() {
		return "property-unixtimestamp";
	}

	/**
	 * 实现： 获取节点实例
	 */
	@Override
	protected UnixTimestampPropertyElement getElementInstance(String data, String alias) {
		return new UnixTimestampPropertyElement(data, alias);
	}

	/**
	 * 实现：增加其它属性处理
	 */
	@Override
	protected void addOtherAttributes(UnixTimestampPropertyElement element, Attributes attributes) {
		element.setNullHidden(attributes.getValue("null-hidden"));
		element.setConvertor(attributes.getValue("convertor"));
	}

	/**
	 * 实现：其它处理
	 */
	@Override
	protected void otherOperation(ParserContext context, UnixTimestampPropertyElement element) {
		// no operation
	}

}
