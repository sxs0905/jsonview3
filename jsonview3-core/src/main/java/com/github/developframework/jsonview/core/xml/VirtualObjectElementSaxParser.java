package com.github.developframework.jsonview.core.xml;

import org.xml.sax.Attributes;

import com.github.developframework.jsonview.core.element.VirtualObjectElement;

/**
 * A parser for xml element: virtual-object
 * 
 * @author qiuzhenhao
 *
 */
public class VirtualObjectElementSaxParser extends ContainerElementSaxParser<VirtualObjectElement> {

	@Override
	public String qName() {
		return "virtual-object";
	}

	@Override
	protected VirtualObjectElement getElementInstance(String data, String alias) {
		return new VirtualObjectElement(alias);
	}

	@Override
	protected void addOtherAttributes(VirtualObjectElement element, Attributes attributes) {
		// no operation
	}

}
