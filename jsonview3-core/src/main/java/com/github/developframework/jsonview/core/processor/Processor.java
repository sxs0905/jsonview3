package com.github.developframework.jsonview.core.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.developframework.jsonview.core.element.Element;
import com.github.developframework.jsonview.data.Expression;

/**
 * abstract root processor
 * 
 * @author qiuzhenhao
 *
 * @param <ELEMENT> XML element Type
 * @param <NODE> JsonNode
 */
public abstract class Processor<ELEMENT extends Element, NODE extends JsonNode> {

	protected Context context;
	protected ELEMENT element;
	protected NODE node;
	protected Expression expression;

	public Processor(Context context, ELEMENT element, Expression parentExpression) {
		this.context = context;
		this.element = element;
		this.expression = createExpression(parentExpression);
	}

	/**
	 * create expression
	 * 
	 * @param parentExpression parent element expression
	 * @return new expression
	 */
	protected abstract Expression createExpression(Expression parentExpression);

	/**
	 * Deal with the operation of child element
	 * 
	 * @param parentProcessor parent element processor
	 */
	protected abstract void process(DescribeContentProcessor<? extends Element, ? extends JsonNode> parentProcessor);

	public Context getContext() {
		return context;
	}

	public ELEMENT getElement() {
		return element;
	}

	public void setNode(NODE node) {
		this.node = node;
	}

	public NODE getNode() {
		return node;
	}

	public Expression getExpression() {
		return expression;
	}

}
