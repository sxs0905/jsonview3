package com.github.developframework.jsonview.core.xml;

import org.xml.sax.Attributes;

import com.github.developframework.jsonview.core.element.ContainerElement;
import com.github.developframework.jsonview.core.element.Element;

/**
 * A parser for describe content xml element
 * 
 * @author qiuzhenhao
 *
 * @param <T> XML element class
 */
abstract class DescribeContentElementSaxParser<T extends Element> implements ElementSaxParser {

	@Override
	public void handleAtStartElement(ParserContext context, Attributes attributes) {
		final String data = attributes.getValue("data");
		final String alias = attributes.getValue("alias");
		// extension: get element instance
		T element = getElementInstance(data, alias);
		// extension： add other attributes
		addOtherAttributes(element, attributes);
		((ContainerElement) context.getStack().peek()).addChildElement(element);
		// extension： other operation
		otherOperation(context, element);
	}

	@Override
	public void handleAtEndElement(ParserContext context) {
		// no operation
	}

	/**
	 * extension: get element instance
	 * 
	 * @param data data string
	 * @param alias alias string
	 * @return element instance
	 */
	protected abstract T getElementInstance(String data, String alias);

	/**
	 * extension： add other attributes
	 * 
	 * @param element element
	 * @param attributes attributes
	 */
	protected abstract void addOtherAttributes(T element, Attributes attributes);

	/**
	 * extension： other operation
	 * 
	 * @param context context
	 * @param element element
	 */
	protected abstract void otherOperation(ParserContext context, T element);
}
