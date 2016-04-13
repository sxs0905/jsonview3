package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.BooleanPropertyElement;
import org.xml.sax.Attributes;

/**
 * 布尔属性节点解析器
 * 
 * @author qiuzhenhao
 *
 */
public class BooleanPropertyElementSaxParser extends DescribeContentElementSaxParser<BooleanPropertyElement> {

	@Override
	public String qName() {
		return "property-boolean";
	}

	/**
	 * 实现： 获取节点实例
	 */
	@Override
	protected BooleanPropertyElement getElementInstance(String data, String alias) {
		return new BooleanPropertyElement(data, alias);
	}

	/**
	 * 实现：增加其它属性处理
	 */
	@Override
	protected void addOtherAttributes(BooleanPropertyElement element, Attributes attributes) {
		element.setNullHidden(attributes.getValue("null-hidden"));
		element.setConvertor(attributes.getValue("convertor"));
	}

	/**
	 * 实现：其它处理
	 */
	@Override
	protected void otherOperation(ParserContext context, BooleanPropertyElement element) {
		// no operation
	}

}
