package org.developframework.jsonview.core.processor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.developframework.jsonview.core.element.ObjectElement;
import org.developframework.jsonview.exception.LinkObjectSizeNotEqualException;

public class LinkObjectProcessor extends ObjectProcessor {

	private int index;

	public LinkObjectProcessor(Context context, ObjectElement element, String parentExpression) {
		super(context, element, parentExpression);
	}

	public void checkSize(int parentArraySize) {
		Optional<Object> objOptional = context.getDataModel().getData(expression);
		if (objOptional.isPresent()) {
			int size = 0;
			Object obj = objOptional.get();
			if (obj.getClass().isArray()) {
				size = ((Object[]) obj).length;
			} else if (obj instanceof Collection<?>) {
				size = ((List<?>) obj).size();
			}
			if (size != parentArraySize) {
				throw new LinkObjectSizeNotEqualException(element.getData());
			}
		}
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
		this.expression += "[" + index + "]";
	}

}
