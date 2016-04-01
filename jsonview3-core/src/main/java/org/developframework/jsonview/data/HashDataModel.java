package org.developframework.jsonview.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.developframework.jsonview.utils.ExpressionUtils;

public class HashDataModel implements DataModel {

	private Map<String, Object> dataMap = new HashMap<String, Object>();

	public HashDataModel() {
	}

	public HashDataModel(Map<String, Object> dataMap) {
		this.dataMap.putAll(dataMap);
	}

	@Override
	public void putData(String dataName, Object data) {
		this.dataMap.put(dataName, data);
	}

	@Override
	public Optional<Object> getData(String expression) {
		final Object value = ExpressionUtils.getValue(dataMap, expression);
		return Optional.ofNullable(value);
	}

}
