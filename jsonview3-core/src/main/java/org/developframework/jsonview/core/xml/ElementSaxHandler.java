package org.developframework.jsonview.core.xml;

import org.xml.sax.Attributes;

interface ElementSaxHandler {

	public String qName();

	public void handleAtStartElement(ParserContext context, Attributes attributes);

	public void handleAtEndElement(ParserContext context);

}
