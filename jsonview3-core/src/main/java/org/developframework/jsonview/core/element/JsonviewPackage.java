package org.developframework.jsonview.core.element;

import java.util.HashMap;
import java.util.Optional;

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

	public Optional<Jsonview> getJsonviewById(String id) {
		Optional<Jsonview> jsonviewOptional = Optional.ofNullable(get(id));
		return jsonviewOptional;
	}

	public void push(Jsonview jsonview) {
		super.put(jsonview.getId(), jsonview);
	}

}
