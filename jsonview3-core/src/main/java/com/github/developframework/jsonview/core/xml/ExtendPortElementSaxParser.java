package com.github.developframework.jsonview.core.xml;

import org.xml.sax.Attributes;

import com.github.developframework.jsonview.core.element.ContainerElement;
import com.github.developframework.jsonview.core.element.ExtendPortElement;

/**
 * A parser for xml element: extend-port
 * 
 * @author qiuzhenhao
 * @since 3.1.0
 */
public class ExtendPortElementSaxParser implements ElementSaxParser {

	@Override
	public String qName() {
		return "extend-port";
	}

	@Override
	public void handleAtStartElement(ParserContext context, Attributes attributes) {
		final String portName = attributes.getValue("port-name");
		final ExtendPortElement extendPortElement = new ExtendPortElement(portName);
		((ContainerElement) context.getStack().peek()).addChildElement(extendPortElement);
	}

	@Override
	public void handleAtEndElement(ParserContext context) {
		// no operation
	}

}
