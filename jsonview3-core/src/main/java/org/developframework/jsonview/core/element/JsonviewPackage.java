package org.developframework.jsonview.core.element;

import java.util.HashMap;
import java.util.Optional;

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
	 * @param id
	 * @return
	 */
	public Optional<Jsonview> getJsonviewById(String id) {
		Optional<Jsonview> jsonviewOptional = Optional.ofNullable(get(id));
		return jsonviewOptional;
	}

	/**
	 * 压入jsonview视图
	 * 
	 * @param jsonview
	 */
	public void push(Jsonview jsonview) {
		super.put(jsonview.getId(), jsonview);
	}

}
