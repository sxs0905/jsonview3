package com.github.developframework.jsonview.core.processor;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.developframework.jsonview.core.element.PropertyElement;
import com.github.developframework.jsonview.data.Expression;

/**
 * A processor for normal property structure
 * 
 * @author qiuzhenhao
 *
 */
public class NormalPropertyProcessor extends PropertyProcessor {

	public NormalPropertyProcessor(Context context, PropertyElement element, Expression parentExpression) {
		super(context, element, parentExpression);
	}

	@Override
	protected boolean support(Class<?> sourceClass) {
		// Here always allow
		return true;
	}

	@Override
	protected void handle(ObjectNode parentNode, Class<?> clazz, Object value, String showName) {
		if (clazz == String.class) {
			parentNode.put(showName, (String) value);
		} else if (clazz == Integer.class) {
			parentNode.put(showName, (Integer) value);
		} else if (clazz == Long.class) {
			parentNode.put(showName, (Long) value);
		} else if (clazz == Boolean.class) {
			parentNode.put(showName, (Boolean) value);
		} else if (clazz == Float.class) {
			parentNode.put(showName, (Float) value);
		} else if (clazz == Double.class) {
			parentNode.put(showName, (Double) value);
		} else if (clazz == BigDecimal.class) {
			parentNode.put(showName, (BigDecimal) value);
		} else {
			parentNode.put(showName, value.toString());
		}
	}

}
