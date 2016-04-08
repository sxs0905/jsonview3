package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.NormalPropertyElement;
import org.developframework.jsonview.core.element.PropertyElement;
import org.xml.sax.Attributes;

class PropertyElementSaxHandler extends DescribeContentElementSaxHandler<PropertyElement> {

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
		element.setHandler(attributes.getValue("handler"));
	}

	@Override
	protected void otherOperation(ParserContext context, PropertyElement element) {
		// no operation
	}

}
