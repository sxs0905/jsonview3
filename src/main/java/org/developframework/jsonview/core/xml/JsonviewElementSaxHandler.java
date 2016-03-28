package org.developframework.jsonview.core.xml;

import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.Jsonview;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.element.JsonviewPackage;
import org.developframework.jsonview.exception.ResourceNotUniqueException;
import org.xml.sax.Attributes;

class JsonviewElementSaxHandler extends ContainerElementSaxHandler implements ElementSaxHandler {

	@Override
	public String qName() {
		return "jsonview";
	}

	@Override
	public JsonviewPackage handleAtStartElement(JsonviewConfiguration configuration, JsonviewPackage jsonviewPackage, Stack<Element> stack, Attributes attributes) {
		final String id = attributes.getValue("id").trim();
		final String data = attributes.getValue("data");
		final Jsonview jsonview = new Jsonview(id);
		if (StringUtils.isNotBlank(data)) {
			jsonview.setData(data.trim());
		}
		final String className = attributes.getValue("for-class");
		forClass(jsonview, className);
		stack.push(jsonview);
		return jsonviewPackage;
	}

	@Override
	public JsonviewPackage handleAtEndElement(JsonviewConfiguration configuration, JsonviewPackage jsonviewPackage, Stack<Element> stack) {
		Jsonview jsonview = (Jsonview) stack.pop();
		if (jsonviewPackage.containsKey(jsonview.getId())) {
			throw new ResourceNotUniqueException(String.format("Jsonview id \"%s\" already exists.", jsonview.getId()));
		}
		jsonview.loadClassProperty();
		jsonviewPackage.push(jsonview);
		return jsonviewPackage;
	}

}
