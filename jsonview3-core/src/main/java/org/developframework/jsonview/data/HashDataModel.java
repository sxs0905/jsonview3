package org.developframework.jsonview.data;

import java.util.HashMap;
import java.util.Map;

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
	public Object getData(String expression) {
		return ExpressionUtils.getValue(dataMap, expression);
	}

}
