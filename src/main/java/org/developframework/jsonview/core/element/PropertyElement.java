package org.developframework.jsonview.core.element;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.Processor;
import org.developframework.jsonview.core.processor.PropertyProcessor;

import com.fasterxml.jackson.databind.JsonNode;

public class PropertyElement extends Element {

	private Class<?> handler;

	public PropertyElement(String parentExpression, String bind) {
		super(parentExpression, bind);
	}

	@Override
	protected String createExpression(String parentExpression, String bind) {
		if (bind.startsWith("#")) {
			return bind.substring(1) + bind;
		}
		return StringUtils.isBlank(parentExpression) ? bind : (parentExpression + "." + bind);
	}

	@Override
	public Processor<? extends Element, ? extends JsonNode> createProcessor(Context context, JsonNode jsonNode) {
		return new PropertyProcessor(context, this);
	}

	public Class<?> getHandler() {
		return handler;
	}

	public void setHandler(Class<?> handler) {
		this.handler = handler;
	}
}
