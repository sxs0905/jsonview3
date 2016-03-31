package org.developframework.jsonview.exception;

public class ConfigurationFileNotFoundException extends JsonviewException {

	private static final long serialVersionUID = -2470097294022069914L;

	public ConfigurationFileNotFoundException(String filename) {
		super("Jsonview framework load the configuration is failed, because not found: " + filename);
	}

}
