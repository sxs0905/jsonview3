package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;

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

	protected Expression createExpression(Expression parentExpression) {
		if (element.getData().startsWith("#")) {
			return Expression.buildObjectExpression(element.getData().substring(1));
		}
		return Expression.concatExpression(parentExpression, element.getData());
	}

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
