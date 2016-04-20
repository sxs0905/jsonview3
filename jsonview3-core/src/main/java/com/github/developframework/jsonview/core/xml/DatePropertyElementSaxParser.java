package com.github.developframework.jsonview.core.xml;

import org.xml.sax.Attributes;

import com.github.developframework.jsonview.core.element.DatePropertyElement;

/**
 * A parser for xml element: property-date
 * 
 * @author qiuzhenhao
 *
 */
class DatePropertyElementSaxParser extends DescribeContentElementSaxParser<DatePropertyElement> {

	@Override
	public String qName() {
		return "property-date";
	}

	@Override
	protected DatePropertyElement getElementInstance(String data, String alias) {
		return new DatePropertyElement(data, alias);
	}

	@Override
	protected void addOtherAttributes(DatePropertyElement element, Attributes attributes) {
		element.setPattern(attributes.getValue("pattern"));
		element.setNullHidden(attributes.getValue("null-hidden"));
		element.setConvertor(attributes.getValue("convertor"));
	}

	@Override
	protected void otherOperation(ParserContext context, DatePropertyElement element) {
		// no operation
	}

}
