package com.github.developframework.jsonview.core.element;

import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.developframework.jsonview.core.processor.Context;
import com.github.developframework.jsonview.core.processor.DatePropertyProcessor;
import com.github.developframework.jsonview.core.processor.Processor;
import com.github.developframework.jsonview.data.Expression;

/**
 * property-date
 * 
 * @author qiuzhenhao
 *
 */
public class DatePropertyElement extends PropertyElement {

	private String pattern;

	public DatePropertyElement(String data, String alias) {
		super(data, alias);
	}

	@Override
	public Optional<Processor<? extends Element, ? extends JsonNode>> createProcessor(Context context, ObjectNode parentNode, Expression parentExpression) {
		DatePropertyProcessor processor = new DatePropertyProcessor(context, this, parentExpression, pattern);
		processor.setNode(parentNode);
		return Optional.of(processor);
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
