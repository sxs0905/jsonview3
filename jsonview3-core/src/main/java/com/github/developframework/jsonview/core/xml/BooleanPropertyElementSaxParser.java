package com.github.developframework.jsonview.core.xml;

import org.xml.sax.Attributes;

import com.github.developframework.jsonview.core.element.BooleanPropertyElement;

/**
 * A parser for xml element: property-boolean
 * 
 * @author qiuzhenhao
 *
 */
public class BooleanPropertyElementSaxParser extends DescribeContentElementSaxParser<BooleanPropertyElement> {

	@Override
	public String qName() {
		return "property-boolean";
	}

	@Override
	protected BooleanPropertyElement getElementInstance(String data, String alias) {
		return new BooleanPropertyElement(data, alias);
	}

	@Override
	protected void addOtherAttributes(BooleanPropertyElement element, Attributes attributes) {
		element.setNullHidden(attributes.getValue("null-hidden"));
		element.setConverter(attributes.getValue("converter"));
	}

	@Override
	protected void otherOperation(ParserContext context, BooleanPropertyElement element) {
		// no operation
	}

}
