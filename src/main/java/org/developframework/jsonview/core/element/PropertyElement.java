package org.developframework.jsonview.core.element;

import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.Processor;
import org.developframework.jsonview.core.processor.PropertyProcessor;

import com.fasterxml.jackson.databind.JsonNode;

public class PropertyElement extends Element {

	private Class<?> handler;

	public PropertyElement(String data) {
		super(data);
	}

	@Override
	public Processor<? extends Element, ? extends JsonNode> createProcessor(Context context, JsonNode jsonNode, String parentExpression) {
		return new PropertyProcessor(context, this, parentExpression);
	}

	public Class<?> getHandler() {
		return handler;
	}

	public void setHandler(Class<?> handler) {
		this.handler = handler;
	}
}
