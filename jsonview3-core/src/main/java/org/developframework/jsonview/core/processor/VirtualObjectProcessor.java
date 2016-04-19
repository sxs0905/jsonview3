package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.ObjectElement;
import org.developframework.jsonview.data.Expression;

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
