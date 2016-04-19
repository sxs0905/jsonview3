package org.developframework.jsonview.data;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * 数据模型
 * 
 * @author qiuzhenhao
 *
 */
public interface DataModel extends Serializable {

	/**
	 * 压入数据
	 * 
	 * @param dataName 数据绑定名称
	 * @param data 数据值
	 */
	public void putData(String dataName, Object data);

	/**
	 * 获取数据
	 * 
	 * @param expression 表达式
	 * @return 数据的Optional对象
	 */
	public Optional<Object> getData(Expression expression);

	/**
	 * 获取数据
	 * 
	 * @param expression 表达式
	 * @return 数据的Optional对象
	 */
	public Optional<Object> getData(String expression);

	/**
	 * 获取数据
	 * 
	 * @param expression 表达式
	 * @param target 目标
	 * @param sourceValue 源值
	 * @return 数据的Optional对象
	 */
	public Optional<List<Expression>> getData(Expression expression, String target, Object sourceValue);
}
