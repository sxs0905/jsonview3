package org.developframework.jsonview.exception;

public class LinkObjectSizeNotEqualException extends JsonviewException {

	private static final long serialVersionUID = 3069634034373263068L;

	public LinkObjectSizeNotEqualException(String data) {
		super(String.format("The element link-object \"%s\"'s size is not equals parent array size.", data));
	}
}
