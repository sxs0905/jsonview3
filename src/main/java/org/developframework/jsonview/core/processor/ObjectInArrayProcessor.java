package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.ObjectElement;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ObjectInArrayProcessor extends ObjectProcessor {

	private int index;

	public ObjectInArrayProcessor(Context context, ObjectElement element, ObjectNode node, String parentExpression, int index) {
		super(context, element, node, parentExpression + "[" + index + "]");
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public String createExpression(String parentExpression) {
		return parentExpression;
	}
}
