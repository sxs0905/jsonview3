package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.BooleanPropertyElement;
import org.xml.sax.Attributes;

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
		element.setConvertor(attributes.getValue("convertor"));
	}

	@Override
	protected void otherOperation(ParserContext context, BooleanPropertyElement element) {
		// no operation
	}

}
