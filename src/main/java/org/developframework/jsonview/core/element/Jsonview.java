package org.developframework.jsonview.core.element;

public class Jsonview extends ObjectElement {

	private String id;

	public Jsonview(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	// @Override
	// protected String createExpression(String parentExpression, String bind) {
	// return null;
	// }

}
