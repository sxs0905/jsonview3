package org.developframework.jsonview.core.element;

public class Jsonview extends ObjectElement {

	private String id;

	public Jsonview(String id) {
		super(null);
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
