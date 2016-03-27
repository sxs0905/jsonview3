package org.developframework.jsonview.core.processor;

import java.util.Iterator;

import org.developframework.jsonview.core.element.ArrayElement;
import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.ObjectElement;
import org.developframework.jsonview.core.element.PropertyElement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ObjectProcessor extends ContainerProcessor<ObjectElement, ObjectNode> {

	public ObjectProcessor(Context context, ObjectElement element, ObjectNode node) {
		super(context, element, node);
	}

	@Override
	public void process(Processor<? extends Element, ? extends JsonNode> parentProcessor) {
		for (Iterator<Element> iterator = super.element.elementIterator(); iterator.hasNext();) {
			Element childElement = iterator.next();
			Processor<? extends Element, ? extends JsonNode> nextProcessor = this.checkNextProcessor(super.element, childElement);
			nextProcessor.process(this);
		}
	}

	private Processor<? extends Element, ? extends JsonNode> checkNextProcessor(Element element, Element childElement) {
		Processor<? extends Element, ? extends JsonNode> nextProcessor = null;
		if (childElement instanceof PropertyElement) {

			nextProcessor = new PropertyProcessor(context, (PropertyElement) childElement);
		} else if (childElement instanceof ObjectElement) {

			ObjectNode objectNode = node.putObject(childElement.showName());
			nextProcessor = new ObjectProcessor(context, (ObjectElement) childElement, objectNode);
		} else if (childElement instanceof ArrayElement) {

		}
		return nextProcessor;
	}

}
