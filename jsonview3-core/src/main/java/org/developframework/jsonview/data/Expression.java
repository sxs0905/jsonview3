package org.developframework.jsonview.data;

import java.util.Objects;

public class Expression {

	private String property;
	private Integer index;

	public Expression(String property) {
		this.property = property;
	}

	public Expression(String property, Integer index) {
		this.property = property;
		this.index = index;
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
		Objects.requireNonNull(property);
		return isArray() ? property + "[" + index + "]" : property;
	}
}
