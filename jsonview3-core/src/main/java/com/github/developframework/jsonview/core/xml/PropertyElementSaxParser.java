package com.github.developframework.jsonview.core.xml;

import org.xml.sax.Attributes;

import com.github.developframework.jsonview.core.element.NormalPropertyElement;
import com.github.developframework.jsonview.core.element.PropertyElement;

/**
 * A parser for xml element: property
 * 
 * @author qiuzhenhao
 *
 */
class PropertyElementSaxParser extends DescribeContentElementSaxParser<PropertyElement> {

	@Override
	public String qName() {
		return "property";
	}

	@Override
	protected PropertyElement getElementInstance(String data, String alias) {
		return new NormalPropertyElement(data, alias);
	}

	@Override
	protected void addOtherAttributes(PropertyElement element, Attributes attributes) {
		element.setNullHidden(attributes.getValue("null-hidden"));
		element.setConvertor(attributes.getValue("convertor"));
	}

	@Override
	protected void otherOperation(ParserContext context, PropertyElement element) {
		// no operation
	}

}
