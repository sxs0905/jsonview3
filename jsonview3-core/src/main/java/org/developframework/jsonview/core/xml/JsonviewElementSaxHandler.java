package org.developframework.jsonview.core.xml;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.element.Jsonview;
import org.developframework.jsonview.exception.ResourceNotUniqueException;
import org.xml.sax.Attributes;

class JsonviewElementSaxHandler extends ContainerElementSaxHandler<Jsonview> {

	@Override
	public String qName() {
		return "jsonview";
	}

	@Override
	public void handleAtStartElement(ParserContext context, Attributes attributes) {
		final String id = attributes.getValue("id").trim();
		final String data = attributes.getValue("data");
		final Jsonview jsonview = new Jsonview(id);
		if (StringUtils.isNotBlank(data)) {
			jsonview.setData(data.trim());
		}
		forClass(jsonview, attributes);
		context.getStack().push(jsonview);
	}

	@Override
	public void handleAtEndElement(ParserContext context) {
		Jsonview jsonview = (Jsonview) context.getStack().pop();
		if (context.getJsonviewPackage().containsKey(jsonview.getId())) {
			throw new ResourceNotUniqueException(String.format("Jsonview id \"%s\" already exists.", jsonview.getId()));
		}
		jsonview.loadClassProperty();
		context.getJsonviewPackage().push(jsonview);
	}

	@Override
	protected Jsonview getElementInstance(String data, String alias) {
		return null;
	}

	@Override
	protected void addOtherAttributes(Jsonview element, Attributes attributes) {

	}

}
