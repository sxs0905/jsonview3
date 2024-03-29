package com.github.developframework.jsonview.data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.github.developframework.jsonview.exception.JsonviewNoSuchFieldException;
import com.github.developframework.jsonview.utils.ExpressionUtils;

/**
 * A dataModel implementation for Hash
 * 
 * @author qiuzhenhao
 *
 */
public class HashDataModel implements DataModel {

	private static final long serialVersionUID = 1824745473338821506L;
	private Map<String, Object> dataMap = new HashMap<String, Object>();

	/**
	 * empty constructor
	 */
	public HashDataModel() {
	}

	/**
	 * copy other data in map
	 * 
	 * @param dataMap other map
	 */
	public HashDataModel(Map<String, Object> dataMap) {
		this.dataMap.putAll(dataMap);
	}

	@Override
	public void putData(String dataName, Object data) {
		this.dataMap.put(dataName, data);
	}

	@Override
	public Optional<Object> getData(Expression expression) {
		final Object value = ExpressionUtils.getValue(dataMap, expression);
		return Optional.ofNullable(value);
	}

	@Override
	public Optional<Object> getData(String expression) {
		return getData(Expression.buildObjectExpression(expression));
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	@Override
	public Optional<List<Expression>> getData(Expression property, String target, Object sourceValue) {
		Optional<Object> objOptional = getData(property);
		if (objOptional.isPresent()) {
			Object obj = objOptional.get();
			List<Expression> expressionList = new LinkedList<>();
			if (obj.getClass().isArray()) {
				Object[] array = (Object[]) obj;
				for (int i = 0; i < array.length; i++) {
					sinple(target, sourceValue, expressionList, array[i], property, i);

				}
			} else if (obj instanceof List<?>) {
				List<?> list = (List<?>) obj;
				for (int i = 0, size = list.size(); i < size; i++) {
					sinple(target, sourceValue, expressionList, list.get(i), property, i);
				}
			} else {
				return Optional.empty();
			}
			return Optional.of(expressionList);
		}
		return Optional.empty();
	}

	private void sinple(String target, Object sourceValue, List<Expression> expressionList, Object object, Expression property, int index) {
		try {
			Field field = object.getClass().getDeclaredField(target);
			field.setAccessible(true);
			Object o = field.get(object);
			if (o == sourceValue || o.equals(sourceValue)) {
				expressionList.add(Expression.buildArrayExpression(property, index));
			}
		} catch (NoSuchFieldException e) {
			throw new JsonviewNoSuchFieldException(target);
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
