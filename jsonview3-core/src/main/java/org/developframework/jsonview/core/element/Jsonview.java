package org.developframework.jsonview.core.element;
/**
 * jsonview节点
 * 
 * @author qiuzhenhao
 *
 */
public class Jsonview extends ObjectElement {

	// 命名空间
	private String namespace;
	// jsonview id
	private String id;

	public Jsonview(String namespace, String id) {
		this.namespace = namespace;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getNamespace() {
		return namespace;
	}

}
