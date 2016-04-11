package org.developframework.jsonview.core.element;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.developframework.jsonview.exception.JsonviewNotFoundException;
import org.developframework.jsonview.exception.JsonviewPackageNotFoundException;

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
		this.jsonviewPackages.put(jsonviewPackage.getNamespace(), jsonviewPackage);
	}

	/**
	 * 获取包
	 * 
	 * @param namespace
	 * @return
	 */
	public Optional<JsonviewPackage> getJsonviewPackageByNamespace(String namespace) {
		return Optional.ofNullable(jsonviewPackages.get(namespace));
	}

	/**
	 * 提取jsonview视图
	 * 
	 * @param namespace
	 * @param id
	 * @return
	 */
	public Jsonview extractJsonview(String namespace, String id) {
		Optional<JsonviewPackage> jsonviewPackageOptional = getJsonviewPackageByNamespace(namespace);
		Optional<Jsonview> jsonviewOptional = jsonviewPackageOptional.orElseThrow(() -> new JsonviewPackageNotFoundException(namespace)).getJsonviewById(id);
		return jsonviewOptional.orElseThrow(() -> new JsonviewNotFoundException(id, namespace));
	}

}
