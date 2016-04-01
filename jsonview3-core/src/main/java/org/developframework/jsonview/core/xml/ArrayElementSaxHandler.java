package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.ArrayElement;
import org.xml.sax.Attributes;

class ArrayElementSaxHandler extends ContainerElementSaxHandler<ArrayElement> {

	@Override
	public String qName() {
		return "array";
	}

	@Override
	protected ArrayElement getElementInstance(String data, String alias) {
		return new ArrayElement(data, alias);
	}

	@Override
	protected void addOtherAttributes(ArrayElement arrayElement, Attributes attributes) {
		arrayElement.setNullHidden(attributes.getValue("null-hidden"));
		forClass(arrayElement, attributes);
	}

}
