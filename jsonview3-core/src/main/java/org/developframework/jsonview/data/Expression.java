package org.developframework.jsonview.data;

import java.util.Objects;

public class Expression {

	private String property;
	private Integer index;

	private Expression(String property) {
		this.property = property;
	}

	private Expression(String property, int index) {
		this.property = property;
		this.index = index;
	}

	public static Expression buildObjectExpression(String property) {
		return new Expression(property);
	}

	public static Expression buildArrayExpression(String property, int index) {
		return new Expression(property, index);
	}

	public static Expression buildArrayExpression(Expression expression, int index) {
		return new Expression(expression.toString(), index);
	}

	public static Expression concatExpression(Expression expression1, Expression expression2) {
		String expression1Str = expression1.toString();
		return new Expression(Objects.isNull(expression1Str) ? expression2.toString() : (expression1Str + "." + expression2.toString()));
	}

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
