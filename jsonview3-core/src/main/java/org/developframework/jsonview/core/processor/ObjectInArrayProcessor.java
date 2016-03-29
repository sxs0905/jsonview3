package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.ObjectElement;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ObjectInArrayProcessor extends ObjectProcessor {

	public ObjectInArrayProcessor(Context context, ObjectElement element, ObjectNode node, String parentExpression, int index) {
		super(context, element, node, parentExpression + "[" + index + "]");
	}

	@Override
	public String createExpression(String parentExpression) {
		return parentExpression;
	}
}
