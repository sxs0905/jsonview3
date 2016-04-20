package com.github.developframework.jsonview.core.xml;

import org.xml.sax.Attributes;

import com.github.developframework.jsonview.core.element.ArrayElement;

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
