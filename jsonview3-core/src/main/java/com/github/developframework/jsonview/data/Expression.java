package com.github.developframework.jsonview.data;

import java.util.Objects;

/**
 * Expression for DataModel, You can get value in DataModel use Expression
 * instance
 * 
 * @author qiuzhenhao
 *
 */
public class Expression {

	// property name
	private String property;
	// array index
	private Integer index;

	private Expression(String property) {
		this.property = property;
	}

	private Expression(String property, int index) {
		this.property = property;
		this.index = index;
	}

	/**
	 * build expression of object structure
	 * 
	 * @param property property
	 * @return new expression
	 */
	public static Expression buildObjectExpression(String property) {
		return new Expression(property);
	}

	/**
	 * build expression of array structure
	 * 
	 * @param property property
	 * @param index array index
	 * @return new expression
	 */
	public static Expression buildArrayExpression(String property, int index) {
		return new Expression(property, index);
	}

	/**
	 * build expression of array structure
	 * 
	 * @param expression expression
	 * @param index array index
	 * @return new expression
	 */
	public static Expression buildArrayExpression(Expression expression, int index) {
		return new Expression(expression.toString(), index);
	}

	/**
	 * concat two expressions
	 * 
	 * @param expression1 expression1
	 * @param expression2 expression2
	 * @return new expression
	 */
	public static Expression concatExpression(Expression expression1, Expression expression2) {
		String expression1Str = Objects.isNull(expression1) ? null : expression1.toString();
		return new Expression(Objects.isNull(expression1Str) ? expression2.toString() : (expression1Str + "." + expression2.toString()));
	}

	/**
	 * concat two expressions
	 * 
	 * @param expression1 expression1
	 * @param expression2 expression2 string
	 * @return new expression
	 */
	public static Expression concatExpression(Expression expression1, String expression2) {
		String expression1Str = Objects.isNull(expression1) ? null : expression1.toString();
		return new Expression(Objects.isNull(expression1Str) ? expression2 : (expression1Str + "." + expression2));
	}

	/**
	 * get property
	 * 
	 * @return property value
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * get array index, if the Expression's structure is object return null
	 * 
	 * @return index value
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * return whether array structure
	 * 
	 * @return boolean
	 */
	public boolean isArray() {
		return Objects.nonNull(index);
	}

	/**
	 * write string
	 */
	@Override
	public String toString() {
		if (Objects.isNull(property)) {
			return null;
		}
		return isArray() ? property + "[" + index + "]" : property;
	}
}
