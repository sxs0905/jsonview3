package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.LinkObjectElement;
import org.xml.sax.Attributes;

/**
 * A parser for xml element: link-object
 * 
 * @author qiuzhenhao
 *
 */
public class LinkObjectElementSaxParser extends ContainerElementSaxParser<LinkObjectElement> {

	@Override
	public String qName() {
		return "link-object";
	}

	@Override
	protected LinkObjectElement getElementInstance(String data, String alias) {
		return new LinkObjectElement(data, alias);
	}

	@Override
	protected void addOtherAttributes(LinkObjectElement element, Attributes attributes) {
		element.setNullHidden(attributes.getValue("null-hidden"));
		forClass(element, attributes);
	}

}
