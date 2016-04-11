package org.developframework.jsonview.data;

import java.util.Objects;

/**
 * 表达式
 * 
 * @author qiuzhenhao
 *
 */
public class Expression {

	// 属性名称
	private String property;
	// 数组索引
	private Integer index;

	private Expression(String property) {
		this.property = property;
	}

	private Expression(String property, int index) {
		this.property = property;
		this.index = index;
	}

	/**
	 * 生成对象表达式
	 * 
	 * @param property
	 * @return
	 */
	public static Expression buildObjectExpression(String property) {
		return new Expression(property);
	}

	/**
	 * 生成数组表达式
	 * 
	 * @param property
	 * @param index
	 * @return
	 */
	public static Expression buildArrayExpression(String property, int index) {
		return new Expression(property, index);
	}

	/**
	 * 生成数组表达式
	 * 
	 * @param expression
	 * @param index
	 * @return
	 */
	public static Expression buildArrayExpression(Expression expression, int index) {
		return new Expression(expression.toString(), index);
	}

	/**
	 * 连接两个表达式
	 * 
	 * @param expression1
	 * @param expression2
	 * @return
	 */
	public static Expression concatExpression(Expression expression1, Expression expression2) {
		String expression1Str = expression1.toString();
		return new Expression(Objects.isNull(expression1Str) ? expression2.toString() : (expression1Str + "." + expression2.toString()));
	}

	/**
	 * 连接两个表达式
	 * 
	 * @param expression1
	 * @param expression2
	 * @return
	 */
	public static Expression concatExpression(Expression expression1, String expression2) {
		String expression1Str = expression1.toString();
		return new Expression(Objects.isNull(expression1Str) ? expression2 : (expression1Str + "." + expression2));
	}

	public String getProperty() {
		return property;
	}

	public Integer getIndex() {
		return index;
	}

	public boolean isArray() {
		return Objects.nonNull(index);
	}

	@Override
	public String toString() {
		if (Objects.isNull(property)) {
			return null;
		}
		return isArray() ? property + "[" + index + "]" : property;
	}
}
