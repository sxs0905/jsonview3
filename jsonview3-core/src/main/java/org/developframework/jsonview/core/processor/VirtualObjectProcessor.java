package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.ObjectElement;
import org.developframework.jsonview.data.Expression;

/**
 * 虚拟对象处理器
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
