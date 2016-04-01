package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.ObjectElement;
import org.xml.sax.Attributes;

class ObjectElementSaxHandler extends ContainerElementSaxHandler<ObjectElement> {

	@Override
	public String qName() {
		return "object";
	}

	@Override
	protected ObjectElement getElementInstance(String data, String alias) {
		return new ObjectElement(data, alias);
	}

	@Override
	protected void addOtherAttributes(ObjectElement objectElement, Attributes attributes) {
		objectElement.setNullHidden(attributes.getValue("null-hidden"));
		forClass(objectElement, attributes);
	}

}
