package com.github.developframework.jsonview.core.element;

import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.developframework.jsonview.core.processor.Context;
import com.github.developframework.jsonview.core.processor.Processor;
import com.github.developframework.jsonview.core.processor.VirtualObjectProcessor;
import com.github.developframework.jsonview.data.Expression;

/**
 * virtual-object
 * 
 * @author qiuzhenhao
 *
 */
public class VirtualObjectElement extends ObjectElement {

	public VirtualObjectElement(String alias) {
		super(null, alias);
	}

	@Override
	public Optional<Processor<? extends Element, ? extends JsonNode>> createProcessor(Context context, ObjectNode parentNode, Expression parentExpression) {
		VirtualObjectProcessor processor = new VirtualObjectProcessor(context, this, parentExpression);
		final ObjectNode objectNode = parentNode.putObject(this.showName());
		processor.setNode(objectNode);
		return Optional.of(processor);
	}

}
