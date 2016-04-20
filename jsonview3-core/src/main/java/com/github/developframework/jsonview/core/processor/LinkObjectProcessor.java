package com.github.developframework.jsonview.core.processor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.github.developframework.jsonview.core.element.ObjectElement;
import com.github.developframework.jsonview.data.Expression;
import com.github.developframework.jsonview.exception.LinkObjectSizeNotEqualException;

/**
 * A processor for link-object structure
 * 
 * @author qiuzhenhao
 *
 */
public class LinkObjectProcessor extends ObjectProcessor {

	// array index
	private int index;

	public LinkObjectProcessor(Context context, ObjectElement element, Expression parentExpression) {
		super(context, element, parentExpression);
	}

	/**
	 * Check whether the number of array elements is the same
	 * 
	 * @param parentArraySize parent array size
	 */
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
		this.expression = Expression.buildArrayExpression(expression, index);
	}

}
