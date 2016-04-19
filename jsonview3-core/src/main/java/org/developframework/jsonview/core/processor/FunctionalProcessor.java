package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.FunctionalElement;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;

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
