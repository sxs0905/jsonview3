package org.developframework.jsonview.data;

import java.util.List;
import java.util.Optional;

public interface DataModel {

	public void putData(String dataName, Object data);

	public Optional<Object> getData(String expression);

	public Optional<List<Expression>> getData(String expression, String target, Object sourceValue);
}
