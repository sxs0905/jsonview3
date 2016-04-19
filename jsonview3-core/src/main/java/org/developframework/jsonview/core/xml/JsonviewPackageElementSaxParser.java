package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.JsonviewPackage;
import org.xml.sax.Attributes;

/**
 * A parser for xml element: jsonview-package
 * 
 * @author qiuzhenhao
 *
 */
class JsonviewPackageElementSaxParser implements ElementSaxParser {

	@Override
	public String qName() {
		return "jsonview-package";
	}

	@Override
	public void handleAtStartElement(ParserContext context, Attributes attributes) {
		final String namespace = attributes.getValue("namespace").trim();
		context.setJsonviewPackage(new JsonviewPackage(namespace));
	}

	@Override
	public void handleAtEndElement(ParserContext context) {
		context.getConfiguration().addJsonviewPackage(context.getJsonviewPackage());
	}

}
