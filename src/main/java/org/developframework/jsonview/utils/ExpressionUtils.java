package org.developframework.jsonview.utils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

import org.developframework.jsonview.exception.JsonviewException;
import org.developframework.jsonview.exception.JsonviewExpressionException;

public final class ExpressionUtils {

	private ExpressionUtils() {
		throw new AssertionError("No org.developframework.jsonview.utils.ExpressionUtils instances for you!");
	}

	public static Object getValue(Object source, String expression) {
		int dotIndex = expression.indexOf(".");
		if (dotIndex == -1) {
			return getPropertyValue(source, expression);
		}
		String property = expression.substring(0, dotIndex);
		String surplusExpression = expression.substring(dotIndex + 1);
		Object value = getPropertyValue(source, property);
		if (Objects.isNull(value)) {
			return null;
		}
		return getValue(value, surplusExpression);
	}

	@SuppressWarnings("unchecked")
	private static Object getPropertyValue(Object source, String property) {
		if (Objects.isNull(source))
			return null;
		if (source instanceof Map) {
			return getPropertyValueFromMap((Map<String, Object>) source, property);
		} else if (source.getClass().isArray()) {
			return getPropertyValueFromArray((Object[]) source, property);
		}
		return getPropertyValueFromObject(source, property);
	}

	private static Object getPropertyValueFromMap(Map<String, Object> map, String property) {
		return map.get(property);
	}

	private static Object getPropertyValueFromArray(Object[] array, String property) {
		if (!property.matches("^.+\\[\\d+\\]$")) {
			throw new JsonviewExpressionException(String.format("\"%s\" isn't a valid array's expression.", property));
		}
		int number = new Integer(property.substring(property.lastIndexOf("[") + 1, property.lastIndexOf("]"))).intValue();
		return array[number];
	}

	private static Object getPropertyValueFromObject(Object source, String property) {
		try {
			Field field = source.getClass().getDeclaredField(property);
			field.setAccessible(true);
			return field.get(source);
		} catch (Exception e) {
			throw new JsonviewException("ExpressionUtils.getValue() is Error.", e);
		}
	}
}
