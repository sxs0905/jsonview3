package org.developframework.jsonview.utils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.data.Expression;
import org.developframework.jsonview.exception.JsonviewException;
import org.developframework.jsonview.exception.JsonviewExpressionException;
import org.developframework.jsonview.exception.JsonviewNoSuchFieldException;

/**
 * 表达式取值工具
 * 
 * @author qiushui
 *
 */
public final class ExpressionUtils {

	private static final String REGEX_ARRAY = "^\\w*(\\[\\d+\\])+$";

	private ExpressionUtils() {
		throw new AssertionError("No " + getClass().getName() + " instances for you!");
	}

	/**
	 * 根据表达式从对象中提取值
	 * 
	 * @param source 数据源
	 * @param expression 表达式
	 * @return 值
	 */
	public static Object getValue(Object source, Expression expression) {
		return getValue(source, expression.toString());
	}

	private static Object getValue(Object source, String expression) {
		int dotIndex = expression.indexOf(".");
		if (dotIndex == -1) {
			return getPropertyValue(source, expression);
		}
		final String property = expression.substring(0, dotIndex);
		final String surplusExpression = expression.substring(dotIndex + 1);
		final Object value = getPropertyValue(source, property);
		if (Objects.isNull(value)) {
			return null;
		}
		return getValue(value, surplusExpression);
	}

	/**
	 * 根据属性名取值
	 * 
	 * @param source 数据源
	 * @param property 属性名
	 * @return 值
	 */
	private static Object getPropertyValue(Object source, String property) {
		if (Objects.isNull(source))
			return null;
		if (property.matches(REGEX_ARRAY)) {
			Object value = getPropertyValueFromArray(source, property);
			property = StringUtils.substringAfter(property, "]");
			if (property.isEmpty()) {
				return value;
			}
			return getValue(value, property);
		}
		return getPropertyValueFromObjectOrMap(source, property);
	}

	/**
	 * 从数组中取值
	 * 
	 * @param source 数据源
	 * @param property 属性名
	 * @return 值
	 */
	private static Object getPropertyValueFromArray(Object source, String property) {
		final String propertyName = StringUtils.substringBefore(property, "[");
		final int number = new Integer(StringUtils.substringBetween(property, "[", "]")).intValue();
		final Object propertyObject = propertyName.isEmpty() ? source : getValue(source, propertyName);
		if (propertyObject instanceof List<?>) {
			return ((List<?>) propertyObject).get(number);
		} else if (propertyObject.getClass().isArray()) {
			return ((Object[]) propertyObject)[number];
		}
		throw new JsonviewExpressionException(String.format("\"%s\" isn't an array or list type.", propertyName));
	}

	/**
	 * 从对象或Map取值
	 * 
	 * @param source 数据源
	 * @param property 属性名
	 * @return 值
	 */
	@SuppressWarnings("unchecked")
	private static Object getPropertyValueFromObjectOrMap(Object source, String property) {
		if (source instanceof Map) {
			return ((Map<String, Object>) source).get(property);
		}
		try {
			final Field field = source.getClass().getDeclaredField(property);
			field.setAccessible(true);
			return field.get(source);
		} catch (NoSuchFieldException e) {
			throw new JsonviewNoSuchFieldException(property);
		} catch (Exception e) {
			throw new JsonviewException("ExpressionUtils.getValue() is Error.", e);
		}
	}
}
