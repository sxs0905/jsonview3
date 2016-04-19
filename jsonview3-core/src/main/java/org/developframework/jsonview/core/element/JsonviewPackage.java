package org.developframework.jsonview.core.element;

import java.util.HashMap;
import java.util.Objects;

import org.developframework.jsonview.exception.JsonviewNotFoundException;
import org.developframework.jsonview.exception.ResourceNotUniqueException;

/**
 * jsonview-package
 * 
 * @author qiuhenhao
 *
 */
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

	/**
	 * get jsonview
	 * 
	 * @param id jsonviewId
	 * @return jsonview
	 */
	public Jsonview getJsonviewById(String id) {
		Jsonview jsonview = get(id);
		if (Objects.isNull(jsonview)) {
			throw new JsonviewNotFoundException(id, namespace);
		}
		return jsonview;
	}

	/**
	 * push jsonview
	 * 
	 * @param jsonview jsonview
	 */
	public void push(Jsonview jsonview) {
		String id = jsonview.getId();
		if (super.containsKey(id)) {
			throw new ResourceNotUniqueException(String.format("Jsonview id \"%s\" is exist.", id));
		}
		super.put(jsonview.getId(), jsonview);
	}

}
