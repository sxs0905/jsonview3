package org.developframework.jsonview.exception;
/**
 * The exception for not found jsonview-package element
 * 
 * @author qiuzhenhao
 *
 */
public class JsonviewPackageNotFoundException extends JsonviewException {

	private static final long serialVersionUID = -4894304990833593340L;

	public JsonviewPackageNotFoundException(String packageName) {
		super(String.format("Jsonview package \"%s\" is not found.", packageName));
	}

}
