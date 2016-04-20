package com.github.developframework.jsonview.data;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * A data container interface
 * 
 * @author qiuzhenhao
 *
 */
public interface DataModel extends Serializable {

	/**
	 * put data in container
	 * 
	 * @param dataName data's name
	 * @param data data's value
	 */
	public void putData(String dataName, Object data);

	/**
	 * get data from container by expression type instance
	 * 
	 * @param expression expression for data
	 * @return data's Optional instance
	 */
	public Optional<Object> getData(Expression expression);

	/**
	 * get data from container by expression string
	 * 
	 * @param expression expression for data
	 * @return data's Optional instance
	 */
	public Optional<Object> getData(String expression);

	/**
	 * get data from container by expression type instance and target
	 * 
	 * @param expression expression for data
	 * @param target target
	 * @param sourceValue source value
	 * @return data's Optional instance
	 */
	public Optional<List<Expression>> getData(Expression expression, String target, Object sourceValue);
}
