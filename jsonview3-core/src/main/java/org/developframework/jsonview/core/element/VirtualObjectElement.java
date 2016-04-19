package org.developframework.jsonview.core.element;

import java.util.Optional;

import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.Processor;
import org.developframework.jsonview.core.processor.VirtualObjectProcessor;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
