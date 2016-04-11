package org.developframework.jsonview.exception;
/**
 * jsonview视图不存在异常
 * 
 * @author qiuzhenhao
 *
 */
public class JsonviewNotFoundException extends JsonviewException {

	private static final long serialVersionUID = -4894304990833593340L;

	public JsonviewNotFoundException(String jsonviewId, String namespace) {
		super(String.format("Jsonview \"%s\" is not Found in namespace \"%s\".", jsonviewId, namespace));
	}

}
