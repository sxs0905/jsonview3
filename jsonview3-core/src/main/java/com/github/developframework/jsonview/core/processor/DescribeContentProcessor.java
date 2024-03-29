package com.github.developframework.jsonview.core.processor;

import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.developframework.jsonview.core.element.DescribeContentElement;
import com.github.developframework.jsonview.data.Expression;

/**
 * The processor for DescribeContentElement
 * 
 * @author qiuzhenhao
 *
 * @param <ELEMENT> xml element type
 * @param <NODE> jsonNode
 */
public abstract class DescribeContentProcessor<ELEMENT extends DescribeContentElement, NODE extends JsonNode> extends Processor<ELEMENT, NODE> {

	public DescribeContentProcessor(Context context, ELEMENT element, Expression parentExpression) {
		super(context, element, parentExpression);
	}

	@Override
	protected Expression createExpression(Expression parentExpression) {
		final String data = element.getData();
		if (Objects.nonNull(data) && data.startsWith("#")) {
			return Expression.buildObjectExpression(element.getData().substring(1));
		}
		return Expression.concatExpression(parentExpression, element.getData());
	}
}
