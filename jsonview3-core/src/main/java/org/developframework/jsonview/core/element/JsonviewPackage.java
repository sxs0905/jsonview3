package org.developframework.jsonview.core.element;

import java.util.HashMap;
import java.util.Objects;

import org.developframework.jsonview.exception.JsonviewNotFoundException;
import org.developframework.jsonview.exception.ResourceNotUniqueException;

/**
 * jsonview的分包（命名空间）
 * 
 * @author qiuhenhao
 *
 */
public class JsonviewPackage extends HashMap<String, Jsonview> {

	private static final long serialVersionUID = -7671014418821915082L;
	// 命名空间
	private String namespace;

	public JsonviewPackage(String namespace) {
		super();
		this.namespace = namespace;
	}

	public String getNamespace() {
		return namespace;
	}

	/**
	 * 获取jsonview
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
	 * 压入jsonview视图
	 * 
	 * @param jsonview jsonview视图
	 */
	public void push(Jsonview jsonview) {
		String id = jsonview.getId();
		if (super.containsKey(id)) {
			throw new ResourceNotUniqueException(String.format("Jsonview id \"%s\" is exist.", id));
		}
		super.put(jsonview.getId(), jsonview);
	}

}
