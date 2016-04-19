package org.developframework.jsonview.exception;
/**
 * The Exception for expression is not correctly
 * 
 * @author qiuzhenhao
 *
 */
public class JsonviewExpressionException extends JsonviewException {

	private static final long serialVersionUID = -4894304990833593340L;

	public JsonviewExpressionException(String message) {
		super(message);
	}

	public JsonviewExpressionException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
