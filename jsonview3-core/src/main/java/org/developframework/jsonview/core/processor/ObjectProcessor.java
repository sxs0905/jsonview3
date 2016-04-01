package org.developframework.jsonview.core.processor;

import java.util.Iterator;
import java.util.Optional;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.ObjectElement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ObjectProcessor extends ContainerProcessor<ObjectElement, ObjectNode> {

	public ObjectProcessor(Context context, ObjectElement element, String parentExpression) {
		super(context, element, parentExpression);
	}

	@Override
	public void process(Processor<? extends Element, ? extends JsonNode> parentProcessor) {
		for (Iterator<Element> iterator = element.elementIterator(); iterator.hasNext();) {
			final Element childElement = iterator.next();
			Optional<Processor<? extends Element, ? extends JsonNode>> nextProcessorOptional = childElement.createProcessor(context, node, expression);
			nextProcessorOptional.ifPresent((nextProcessor) -> nextProcessor.process(this));
		}
	}
}
