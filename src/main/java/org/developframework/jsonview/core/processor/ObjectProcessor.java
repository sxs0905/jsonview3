package org.developframework.jsonview.core.processor;

import java.util.Iterator;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.ObjectElement;
import org.developframework.jsonview.core.element.PropertyElement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ObjectProcessor extends ContainerProcessor<ObjectElement, ObjectNode> {

	public ObjectProcessor(Context context, ObjectElement element, ObjectNode node) {
		super(context, element, node);
		// TODO Auto-generated constructor stub
	}

	private void executeNextProcessor(Element element, Element childElement) {
		if (childElement instanceof PropertyElement) {

		} else if (childElement instanceof ObjectElement) {

		}

	}

	@Override
	protected void process(Processor<? extends Element, ? extends JsonNode> parentProcessor) {
		for (Iterator<Element> iterator = super.element.elementIterator(); iterator.hasNext();) {
			Element childElement = iterator.next();
			this.executeNextProcessor(super.element, childElement);
		}
	}

}
