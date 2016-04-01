package org.developframework.jsonview.core.element;

import java.util.Optional;

import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.NormalPropertyProcessor;
import org.developframework.jsonview.core.processor.Processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class NormalPropertyElement extends PropertyElement {

	public NormalPropertyElement(String data, String alias) {
		super(data, alias);
	}

	@Override
	public Optional<Processor<? extends Element, ? extends JsonNode>> createProcessor(Context context, ObjectNode parentNode, String parentExpression) {
		NormalPropertyProcessor processor = new NormalPropertyProcessor(context, this, parentExpression);
		processor.setNode(parentNode);
		return Optional.of(processor);
	}
}
