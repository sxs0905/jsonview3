package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.VirtualObjectElement;
import org.xml.sax.Attributes;

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
