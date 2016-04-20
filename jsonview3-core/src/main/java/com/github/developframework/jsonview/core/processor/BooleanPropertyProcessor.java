package com.github.developframework.jsonview.core.processor;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.developframework.jsonview.core.element.PropertyElement;
import com.github.developframework.jsonview.data.Expression;

/**
 * A processor for boolean property structure
 * 
 * @author qiuzhenhao
 *
 */
public class BooleanPropertyProcessor extends PropertyProcessor {

	// List of support class
	private static final List<Class<?>> ACCEPT_CLASS_LIST = new ArrayList<>(8);

	public BooleanPropertyProcessor(Context context, PropertyElement element, Expression parentExpression) {
		super(context, element, parentExpression);
		ACCEPT_CLASS_LIST.add(boolean.class);
		ACCEPT_CLASS_LIST.add(Boolean.class);
		ACCEPT_CLASS_LIST.add(int.class);
		ACCEPT_CLASS_LIST.add(Integer.class);
		ACCEPT_CLASS_LIST.add(long.class);
		ACCEPT_CLASS_LIST.add(Long.class);
		ACCEPT_CLASS_LIST.add(short.class);
		ACCEPT_CLASS_LIST.add(Short.class);
	}

	@Override
	protected boolean support(Class<?> sourceClass) {
		return ACCEPT_CLASS_LIST.contains(sourceClass);
	}

	@Override
	protected void handle(ObjectNode parentNode, Class<?> clazz, Object value, String showName) {
		boolean v = false;
		if (clazz == Boolean.class) {
			v = ((Boolean) value).booleanValue();
		} else if (clazz == Integer.class) {
			v = ((Integer) value).intValue() != 0;
		} else if (clazz == Long.class) {
			v = ((Long) value).longValue() != 0;
		} else if (clazz == Short.class) {
			v = ((Short) value).shortValue() != 0;
		} else {
			parentNode.putNull(showName);
			return;
		}
		parentNode.put(showName, v);
	}

}
