package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.ObjectElement;

public class ObjectInArrayProcessor extends ObjectProcessor {

	public ObjectInArrayProcessor(Context context, ObjectElement element, String parentExpression, int index) {
		super(context, element, parentExpression + "[" + index + "]");
	}

	@Override
	public String createExpression(String parentExpression) {
		return parentExpression;
	}
}
