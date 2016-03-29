package org.developframework.jsonview.core.processor;

import java.math.BigDecimal;

import org.developframework.jsonview.core.element.PropertyElement;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class NormalPropertyProcessor extends PropertyProcessor<Object> {

	public NormalPropertyProcessor(Context context, PropertyElement element, String parentExpression) {
		super(context, element, parentExpression);
	}

	@Override
	protected boolean support(Class<?> sourceClass) {
		return true;
	}

	@Override
	protected void handle(ObjectNode parentNode, Object value, String showName) {
		if (value instanceof String) {
			parentNode.put(showName, (String) value);
		} else if (value instanceof Integer) {
			parentNode.put(showName, (Integer) value);
		} else if (value instanceof Long) {
			parentNode.put(showName, (Long) value);
		} else if (value instanceof Boolean) {
			parentNode.put(showName, (Boolean) value);
		} else if (value instanceof Float) {
			parentNode.put(showName, (Float) value);
		} else if (value instanceof Double) {
			parentNode.put(showName, (Double) value);
		} else if (value instanceof BigDecimal) {
			parentNode.put(showName, (BigDecimal) value);
		} else {
			parentNode.put(showName, value.toString());
		}
	}

}
