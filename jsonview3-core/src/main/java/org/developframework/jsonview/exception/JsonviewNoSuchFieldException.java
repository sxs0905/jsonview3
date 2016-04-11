package org.developframework.jsonview.exception;
/**
 * 没有字段异常
 * 
 * @author qiuzhenhao
 *
 */
public class JsonviewNoSuchFieldException extends JsonviewExpressionException {

	private static final long serialVersionUID = 1960893441072689530L;

	public JsonviewNoSuchFieldException(String property) {
		super(String.format("No such field \"%s\" in source instance.", property));
	}

}
