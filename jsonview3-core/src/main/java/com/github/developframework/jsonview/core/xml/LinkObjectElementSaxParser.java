package com.github.developframework.jsonview.core.xml;

import org.xml.sax.Attributes;

import com.github.developframework.jsonview.core.element.LinkObjectElement;

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
