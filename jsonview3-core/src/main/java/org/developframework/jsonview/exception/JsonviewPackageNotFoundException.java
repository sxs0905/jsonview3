package org.developframework.jsonview.exception;
/**
 * 命名空间不存在异常
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
