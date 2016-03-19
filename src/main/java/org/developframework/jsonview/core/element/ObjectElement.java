package org.developframework.jsonview.core.element;

import org.apache.commons.lang3.StringUtils;

public class ObjectElement extends ContainerElement {

	public ObjectElement(String parentExpression, String bind) {
		super(parentExpression, bind);
	}

	@Override
	protected String createExpression(String parentExpression, String bind) {
		if (bind.startsWith("#")) {
			return bind.substring(1) + bind;
		}
		return StringUtils.isBlank(parentExpression) ? bind : (parentExpression + "." + bind);
	}

}
