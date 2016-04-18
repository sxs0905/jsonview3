package org.developframework.jsonview.core.element;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.developframework.jsonview.exception.JsonviewPackageNotFoundException;
import org.developframework.jsonview.exception.ResourceNotUniqueException;

/**
 * jsonview配置项
 * 
 * @author qiuhenhao
 *
 */
public class JsonviewConfiguration {

	// jsonview包集
	private Map<String, JsonviewPackage> jsonviewPackages;

	public JsonviewConfiguration() {
		this.jsonviewPackages = new HashMap<>();
	}

	/**
	 * 增加包
	 * 
	 * @param jsonviewPackage
	 */
	public void addJsonviewPackage(JsonviewPackage jsonviewPackage) {
		String namespace = jsonviewPackage.getNamespace();
		if (this.jsonviewPackages.containsKey(namespace)) {
			throw new ResourceNotUniqueException(String.format("JsonviewPackage namespace \"%s\" is exist.", namespace));
		}
		this.jsonviewPackages.put(namespace, jsonviewPackage);
	}

	/**
	 * 获取包
	 * 
	 * @param namespace
	 * @return
	 */
	public JsonviewPackage getJsonviewPackageByNamespace(String namespace) {
		JsonviewPackage jsonviewPackage = jsonviewPackages.get(namespace);
		if (Objects.isNull(jsonviewPackage)) {
			throw new JsonviewPackageNotFoundException(namespace);
		}
		return jsonviewPackage;
	}

	/**
	 * 提取jsonview视图
	 * 
	 * @param namespace
	 * @param id
	 * @return
	 */
	public Jsonview extractJsonview(String namespace, String id) {
		return getJsonviewPackageByNamespace(namespace).getJsonviewById(id);
	}

}
