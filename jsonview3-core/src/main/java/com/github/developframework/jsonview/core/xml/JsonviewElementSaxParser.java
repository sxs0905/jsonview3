package com.github.developframework.jsonview.core.xml;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;

import com.github.developframework.jsonview.core.element.Jsonview;
import com.github.developframework.jsonview.exception.ResourceNotUniqueException;

/**
 * A parser for xml element: jsonview
 * 
 * @author qiuzhenhao
 *
 */
class JsonviewElementSaxParser extends ContainerElementSaxParser<Jsonview> {

	@Override
	public String qName() {
		return "jsonview";
	}

	@Override
	public void handleAtStartElement(ParserContext context, Attributes attributes) {
		final String id = attributes.getValue("id").trim();
		final String data = attributes.getValue("data");
		final String extend = attributes.getValue("extend");
		final Jsonview jsonview = new Jsonview(context.getJsonviewPackage().getNamespace(), id);
		if (StringUtils.isNotBlank(data)) {
			jsonview.setData(data.trim());
		}
		if (StringUtils.isNotBlank(extend)) {
			String defaultNamespace = context.getJsonviewPackage().getNamespace();
			jsonview.setExtend(jsonview.new Extend(extend.trim(), defaultNamespace));
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
		// no operation
		return null;
	}

	@Override
	protected void addOtherAttributes(Jsonview element, Attributes attributes) {
		// no operation
	}

}
