package org.developframework.jsonview.data;

import java.util.Optional;

public interface DataModel {

	public void putData(String dataName, Object data);

	public Optional<Object> getData(String expression);
}
