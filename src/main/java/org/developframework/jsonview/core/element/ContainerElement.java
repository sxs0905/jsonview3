package org.developframework.jsonview.core.element;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class ContainerElement extends Element {

	protected List<Element> childElements = new LinkedList<>();

	public ContainerElement(String parentExpression, String bind) {
		super(parentExpression, bind);
	}

	public void addElement(Element element) {
		childElements.add(element);
	}

	public Iterator<Element> elementIterator() {
		return childElements.iterator();
	}
}
