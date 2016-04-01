package org.developframework.jsonview.core.element;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

	public Optional<JsonviewPackage> getJsonviewPackageByNamespace(String namespace) {
		return Optional.ofNullable(jsonviewPackages.get(namespace));
	}

	public Jsonview extractJsonview(String namespace, String id) {
		Optional<JsonviewPackage> jsonviewPackageOptional = getJsonviewPackageByNamespace(namespace);
		Optional<Jsonview> jsonviewOptional = jsonviewPackageOptional.orElseThrow(() -> new JsonviewPackageNotFoundException(namespace)).getJsonviewById(id);
		return jsonviewOptional.orElseThrow(() -> new JsonviewNotFoundException(id, namespace));
	}

}
