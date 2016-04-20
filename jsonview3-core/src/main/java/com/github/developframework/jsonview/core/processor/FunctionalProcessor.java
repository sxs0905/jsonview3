package com.github.developframework.jsonview.core.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.developframework.jsonview.core.element.FunctionalElement;
import com.github.developframework.jsonview.data.Expression;

/**
 * The processor for FunctionalElement
 * 
 * @author qiuzhenhao
 *
 * @param <ELEMENT> xml element type
 * @param <NODE> jsonNode
 */
public abstract class FunctionalProcessor<ELEMENT extends FunctionalElement, NODE extends JsonNode> extends Processor<ELEMENT, NODE> {

	public FunctionalProcessor(Context context, ELEMENT element, Expression parentExpression) {
		super(context, element, parentExpression);
	}

	@Override
	protected Expression createExpression(Expression parentExpression) {
		return parentExpression;
	}
}
