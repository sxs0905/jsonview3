package org.developframework.jsonview.core.element;

import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.NormalPropertyProcessor;
import org.developframework.jsonview.core.processor.Processor;

import com.fasterxml.jackson.databind.JsonNode;

public class NormalPropertyElement extends PropertyElement {

	public NormalPropertyElement(String data, String alias) {
		super(data, alias);
	}

	@Override
	public Processor<? extends Element, ? extends JsonNode> createProcessor(Context context, JsonNode jsonNode, String parentExpression) {
		return new NormalPropertyProcessor(context, this, parentExpression);
	}
}
