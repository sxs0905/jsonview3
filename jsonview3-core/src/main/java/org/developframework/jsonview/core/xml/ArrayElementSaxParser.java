package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.ArrayElement;
import org.xml.sax.Attributes;

/**
 * A parser for xml element: array
 * 
 * @author qiuzhenhao
 *
 */
class ArrayElementSaxParser extends ContainerElementSaxParser<ArrayElement> {

	@Override
	public String qName() {
		return "array";
	}

	@Override
	protected ArrayElement getElementInstance(String data, String alias) {
		return new ArrayElement(data, alias);
	}

	@Override
	protected void addOtherAttributes(ArrayElement element, Attributes attributes) {
		element.setNullHidden(attributes.getValue("null-hidden"));
		forClass(element, attributes);
	}

}
