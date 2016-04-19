package org.developframework.jsonview.core.element;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.developframework.jsonview.exception.JsonviewPackageNotFoundException;
import org.developframework.jsonview.exception.ResourceNotUniqueException;

/**
 * jsonview-configuration
 * 
 * @author qiuhenhao
 *
 */
public class JsonviewConfiguration {

	private Map<String, JsonviewPackage> jsonviewPackages;

	public JsonviewConfiguration() {
		this.jsonviewPackages = new HashMap<>();
	}

	/**
	 * add jsonview-package
	 * 
	 * @param jsonviewPackage jsonviewPackage
	 */
	public void addJsonviewPackage(JsonviewPackage jsonviewPackage) {
		String namespace = jsonviewPackage.getNamespace();
		if (this.jsonviewPackages.containsKey(namespace)) {
			throw new ResourceNotUniqueException(String.format("JsonviewPackage namespace \"%s\" is exist.", namespace));
		}
		this.jsonviewPackages.put(namespace, jsonviewPackage);
	}

	/**
	 * get package
	 * 
	 * @param namespace namespace
	 * @return JsonviewPackage
	 */
	public JsonviewPackage getJsonviewPackageByNamespace(String namespace) {
		JsonviewPackage jsonviewPackage = jsonviewPackages.get(namespace);
		if (Objects.isNull(jsonviewPackage)) {
			throw new JsonviewPackageNotFoundException(namespace);
		}
		return jsonviewPackage;
	}

	/**
	 * extract jsonview
	 * 
	 * @param namespace namespace
	 * @param id jsonviewId
	 * @return jsonview
	 */
	public Jsonview extractJsonview(String namespace, String id) {
		return getJsonviewPackageByNamespace(namespace).getJsonviewById(id);
	}

}
