package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.DatePropertyElement;
import org.xml.sax.Attributes;

class DatePropertyElementSaxHandler extends DescribeContentElementSaxHandler<DatePropertyElement> {

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
	}

	@Override
	protected void otherOperation(ParserContext context, DatePropertyElement element) {
		// no operation
	}

}
