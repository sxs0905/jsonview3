package org.developframework.jsonview.data;

import java.util.List;
import java.util.Optional;

/**
 * 数据模型
 * 
 * @author qiuzhenhao
 *
 */
public interface DataModel {

	/**
	 * 压入数据
	 * 
	 * @param dataName
	 * @param data
	 */
	public void putData(String dataName, Object data);

	/**
	 * 获取数据
	 * 
	 * @param expression
	 * @return
	 */
	public Optional<Object> getData(Expression expression);

	/**
	 * 获取数据
	 * 
	 * @param expression
	 * @return
	 */
	public Optional<Object> getData(String expression);

	/**
	 * 获取数据
	 * 
	 * @param expression
	 * @param target
	 * @param sourceValue
	 * @return
	 */
	public Optional<List<Expression>> getData(Expression expression, String target, Object sourceValue);
}
