package com.github.developframework.jsonview.core.xml;

import org.xml.sax.Attributes;

import com.github.developframework.jsonview.core.element.MappingObjectElement;
import com.github.developframework.jsonview.core.element.MappingObjectElement.MappingType;

/**
 * A parser for xml element: mapping-object
 * 
 * @author qiuzhenhao
 *
 */
public class MappingObjectElementSaxParser extends ContainerElementSaxParser<MappingObjectElement> {

	@Override
	public String qName() {
		return "mapping-object";
	}

	@Override
	protected MappingObjectElement getElementInstance(String data, String alias) {
		return new MappingObjectElement(data, alias);
	}

	@Override
	protected void addOtherAttributes(MappingObjectElement element, Attributes attributes) {
		element.setSource(attributes.getValue("source"));
		element.setTarget(attributes.getValue("target"));
		element.setNullHidden(attributes.getValue("null-hidden"));
		element.setMappingType(MappingType.parse(attributes.getValue("type")));
		forClass(element, attributes);
	}

}
