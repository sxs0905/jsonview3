package org.developframework.jsonview.core.xml;

import org.xml.sax.Attributes;

/**
 * The xml parser interface
 * 
 * @author qiuzhenhao
 *
 */
interface ElementSaxParser {

	/**
	 * catch element's qName value
	 * 
	 * @return qName value
	 */
	public String qName();

	/**
	 * handle operrate at sax elememt start
	 * 
	 * @param context parserContext
	 * @param attributes sax attributes
	 */
	public void handleAtStartElement(ParserContext context, Attributes attributes);

	/**
	 * handle operrate at sax elememt end
	 * 
	 * @param context parserContext
	 */
	public void handleAtEndElement(ParserContext context);

}
