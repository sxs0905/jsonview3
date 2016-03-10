package org.developframework.jsonview.core.element;

import java.util.HashMap;
import java.util.Map;

public class JsonviewConfiguration {

	private Map<String, JsonviewPackage> jsonviewPackages;

	public JsonviewConfiguration() {
		this.jsonviewPackages = new HashMap<>();
	}

	public void addJsonviewPackage(JsonviewPackage jsonviewPackage) {
		this.jsonviewPackages.put(jsonviewPackage.getNamespace(), jsonviewPackage);
	}

	public JsonviewPackage getJsonviewPackageByNamespace(String namespace) {
		return jsonviewPackages.get(namespace);
	}

}
