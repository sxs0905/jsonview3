package com.github.developframework.jsonview.core.processor;

import com.github.developframework.jsonview.core.element.ObjectElement;
import com.github.developframework.jsonview.data.Expression;

/**
 * A processor for virtual object structure
 * 
 * @author qiuzhenhao
 *
 */
public class VirtualObjectProcessor extends ObjectProcessor {

	public VirtualObjectProcessor(Context context, ObjectElement element, Expression parentExpression) {
		super(context, element, parentExpression);
	}

	@Override
	protected Expression createExpression(Expression parentExpression) {
		return parentExpression;
	}
}
