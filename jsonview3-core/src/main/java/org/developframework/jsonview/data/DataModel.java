package org.developframework.jsonview.data;

import java.util.List;
import java.util.Optional;

public interface DataModel {

	public void putData(String dataName, Object data);

	public Optional<Object> getData(Expression expression);

	public Optional<List<Expression>> getData(Expression expression, String target, Object sourceValue);
}
