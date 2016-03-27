package org.developframework.jsonview.core.element;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.developframework.jsonview.exception.JsonviewNotFoundException;
import org.developframework.jsonview.exception.JsonviewPackageNotFoundException;

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

	public Jsonview extractJsonview(String namespace, String id) {
		JsonviewPackage jsonviewPackage = jsonviewPackages.get(namespace);
		if (Objects.isNull(jsonviewPackage)) {
			throw new JsonviewPackageNotFoundException(String.format("Jsonview package \"%s\" is not found.", namespace));
		}
		Jsonview jsonview = jsonviewPackage.get(id);
		if (Objects.isNull(jsonview)) {
			throw new JsonviewNotFoundException(String.format("Jsonview \"%s\" is not Found in namespace \"%s\".", id, namespace));
		}
		return jsonview;
	}

}
