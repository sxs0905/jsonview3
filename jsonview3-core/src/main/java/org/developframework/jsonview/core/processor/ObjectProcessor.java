package org.developframework.jsonview.core.processor;

import java.util.Iterator;

import org.developframework.jsonview.core.element.ArrayElement;
import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.ImportElement;
import org.developframework.jsonview.core.element.ObjectElement;
import org.developframework.jsonview.core.element.PropertyElement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ObjectProcessor extends ContainerProcessor<ObjectElement, ObjectNode> {

	public ObjectProcessor(Context context, ObjectElement element, ObjectNode node, String parentExpression) {
		super(context, element, node, parentExpression);
	}

	@Override
	public void process(Processor<? extends Element, ? extends JsonNode> parentProcessor) {
		for (Iterator<Element> iterator = element.elementIterator(); iterator.hasNext();) {
			Element childElement = iterator.next();
			Processor<? extends Element, ? extends JsonNode> nextProcessor = this.checkNextProcessor(childElement);
			nextProcessor.process(this);
		}
	}

	private Processor<? extends Element, ? extends JsonNode> checkNextProcessor(Element childElement) {
		JsonNode jsonNode = null;
		if (childElement instanceof PropertyElement) {
			// no operation.
		} else if (childElement instanceof ObjectElement) {
			// put object node in json tree.
			jsonNode = node.putObject(childElement.showName());
		} else if (childElement instanceof ArrayElement) {
			// put array node in json tree.
			jsonNode = node.putArray(childElement.showName());
		} else if (childElement instanceof ImportElement) {
			jsonNode = node;
		}
		return childElement.createProcessor(context, jsonNode, expression);
	}

}
