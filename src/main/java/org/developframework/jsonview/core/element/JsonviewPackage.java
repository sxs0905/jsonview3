package org.developframework.jsonview.core.element;

import java.util.HashMap;

public class JsonviewPackage extends HashMap<String, Jsonview> {

	private static final long serialVersionUID = -7671014418821915082L;
	private String namespace;

	public JsonviewPackage(String namespace) {
		super();
		this.namespace = namespace;
	}

	public String getNamespace() {
		return namespace;
	}

	public Jsonview getJsonviewById(String id) {
		return get(id);
	}

	public void push(Jsonview jsonview) {
		super.put(jsonview.getId(), jsonview);
	}

}
