package org.developframework.jsonview.core.processor;

import java.util.Objects;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;

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
	protected Expression createExpression(Expression parentExpression) {
		final String data = element.getData();
		if (Objects.nonNull(data) && data.startsWith("#")) {
			return Expression.buildObjectExpression(element.getData().substring(1));
		}
		return Expression.concatExpression(parentExpression, element.getData());
	}

	/**
	 * Deal with the operation of child element
	 * 
	 * @param parentProcessor parent element processor
	 */
	protected abstract void process(Processor<? extends Element, ? extends JsonNode> parentProcessor);

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
