package org.developframework.jsonview.core.element;

import org.apache.commons.lang3.StringUtils;

public class ArrayElement extends ContainerElement {

	public ArrayElement(String parentExpression, String bind) {
		super(parentExpression, bind);
	}

	@Override
	protected String createExpression(String parentExpression, String bind) {
		if (bind.startsWith("#")) {
			return bind.substring(1) + bind + "[%d]";
		}
		return StringUtils.isBlank(parentExpression) ? (bind + "[%d]") : (parentExpression + "." + bind + "[%d]");
	}
}
