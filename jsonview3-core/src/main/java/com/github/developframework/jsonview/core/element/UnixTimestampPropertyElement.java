package com.github.developframework.jsonview.core.element;

import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.developframework.jsonview.core.processor.Context;
import com.github.developframework.jsonview.core.processor.Processor;
import com.github.developframework.jsonview.core.processor.UnixTimestampPropertyProcessor;
import com.github.developframework.jsonview.data.Expression;

/**
 * property-unixtimestamp
 * 
 * @author qiuzhenhao
 *
 */
public class UnixTimestampPropertyElement extends PropertyElement {

	public UnixTimestampPropertyElement(String data, String alias) {
		super(data, alias);
	}

	@Override
	public Optional<Processor<? extends Element, ? extends JsonNode>> createProcessor(Context context, ObjectNode parentNode, Expression parentExpression) {
		UnixTimestampPropertyProcessor processor = new UnixTimestampPropertyProcessor(context, this, parentExpression);
		processor.setNode(parentNode);
		return Optional.of(processor);
	}
}
