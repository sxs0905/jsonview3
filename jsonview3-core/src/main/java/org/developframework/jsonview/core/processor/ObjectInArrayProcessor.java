package org.developframework.jsonview.core.processor;

import java.util.Iterator;
import java.util.Optional;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.ObjectElement;

import com.fasterxml.jackson.databind.JsonNode;

public class ObjectInArrayProcessor extends ObjectProcessor {

	protected int index;
	protected int size;

	public ObjectInArrayProcessor(Context context, ObjectElement element, String parentExpression, int index, int size) {
		super(context, element, parentExpression + "[" + index + "]");
		this.index = index;
		this.size = size;
	}

	@Override
	public void process(Processor<? extends Element, ? extends JsonNode> parentProcessor) {
		for (Iterator<Element> iterator = element.elementIterator(); iterator.hasNext();) {
			final Element childElement = iterator.next();
			final Optional<Processor<? extends Element, ? extends JsonNode>> nextProcessorOptional = childElement.createProcessor(context, node, expression);
			nextProcessorOptional.ifPresent((nextProcessor) -> {
				if (nextProcessor instanceof LinkObjectProcessor) {
					LinkObjectProcessor linkObjectProcessor = (LinkObjectProcessor) nextProcessor;
					linkObjectProcessor.checkSize(size);
					linkObjectProcessor.setIndex(index);
				}
				nextProcessor.process(this);
			});
		}
	}

	@Override
	public String createExpression(String parentExpression) {
		return parentExpression;
	}

	public int getIndex() {
		return index;
	}

	public int getSize() {
		return size;
	}

}
