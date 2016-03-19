package org.developframework.jsonview.core.element;

import org.apache.commons.lang3.StringUtils;

public class PropertyElement extends Element {

	private Class<?> handler;

	public PropertyElement(String parentExpression, String bind) {
		super(parentExpression, bind);
	}

	@Override
	protected String createExpression(String parentExpression, String bind) {
		if (bind.startsWith("#")) {
			return bind.substring(1) + bind;
		}
		return StringUtils.isBlank(parentExpression) ? bind : (parentExpression + "." + bind);
	}

	public Class<?> getHandler() {
		return handler;
	}

	public void setHandler(Class<?> handler) {
		this.handler = handler;
	}
}
