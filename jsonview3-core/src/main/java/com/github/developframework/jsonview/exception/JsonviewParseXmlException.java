package com.github.developframework.jsonview.exception;
/**
 * The exception for configuration file parsing error
 * 
 * @author qiuzhenhao
 *
 */
public class JsonviewParseXmlException extends JsonviewException {

	private static final long serialVersionUID = 6131701003042352198L;

	public JsonviewParseXmlException(String filename) {
		super("Jsonview framework parse XML Error for configuration file: " + filename);
	}

	public JsonviewParseXmlException(String filename, Throwable e) {
		super("Jsonview framework parse XML Error for configuration file: " + filename, e);
	}

}
