package org.developframework.jsonview.data;

public interface DataModel {

	public void putData(String dataName, Object data);

	public Object getData(String expression);
}
