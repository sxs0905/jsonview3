package org.developframework.jsonview.core.element;

import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.DatePropertyProcessor;
import org.developframework.jsonview.core.processor.Processor;

import com.fasterxml.jackson.databind.JsonNode;

public class DatePropertyElement extends PropertyElement {

	private String pattern;

	public DatePropertyElement(String data, String alias, String pattern) {
		super(data, alias);
		this.pattern = pattern;
	}

	@Override
	public Processor<? extends Element, ? extends JsonNode> createProcessor(Context context, JsonNode jsonNode, String parentExpression) {
		return new DatePropertyProcessor(context, this, parentExpression, pattern);
	}

}
