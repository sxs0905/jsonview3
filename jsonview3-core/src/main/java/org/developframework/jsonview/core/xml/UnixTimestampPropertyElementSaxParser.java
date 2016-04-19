package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.UnixTimestampPropertyElement;
import org.xml.sax.Attributes;

/**
 * A parser for xml element: property-unixtimestamp
 * 
 * @author qiuzhenhao
 *
 */
class UnixTimestampPropertyElementSaxParser extends DescribeContentElementSaxParser<UnixTimestampPropertyElement> {

	@Override
	public String qName() {
		return "property-unixtimestamp";
	}

	@Override
	protected UnixTimestampPropertyElement getElementInstance(String data, String alias) {
		return new UnixTimestampPropertyElement(data, alias);
	}

	@Override
	protected void addOtherAttributes(UnixTimestampPropertyElement element, Attributes attributes) {
		element.setNullHidden(attributes.getValue("null-hidden"));
		element.setConvertor(attributes.getValue("convertor"));
	}

	@Override
	protected void otherOperation(ParserContext context, UnixTimestampPropertyElement element) {
		// no operation
	}

}
