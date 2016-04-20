package com.github.developframework.jsonview.core.xml;

import org.xml.sax.Attributes;

import com.github.developframework.jsonview.core.element.ObjectElement;

/**
 * A parser for xml element: object
 * 
 * @author qiuzhenhao
 *
 */
class ObjectElementSaxParser extends ContainerElementSaxParser<ObjectElement> {

	@Override
	public String qName() {
		return "object";
	}

	@Override
	protected ObjectElement getElementInstance(String data, String alias) {
		return new ObjectElement(data, alias);
	}

	@Override
	protected void addOtherAttributes(ObjectElement element, Attributes attributes) {
		element.setNullHidden(attributes.getValue("null-hidden"));
		forClass(element, attributes);
	}

}
