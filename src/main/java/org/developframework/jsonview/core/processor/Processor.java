package org.developframework.jsonview.core.processor;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.element.Element;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class Processor<ELEMENT extends Element, NODE extends JsonNode> {

	protected Context context;
	protected ELEMENT element;
	protected NODE node;
	protected String expression;

	public Processor(Context context, ELEMENT element, NODE node, String parentExpression) {
		this.context = context;
		this.element = element;
		this.node = node;
		this.expression = createExpression(parentExpression);
	}

	protected String createExpression(String parentExpression) {
		if (element.getData().startsWith("#")) {
			return element.getData().substring(1);
		}
		return StringUtils.isBlank(parentExpression) ? element.getData() : (parentExpression + "." + element.getData());
	}

	protected abstract void process(Processor<? extends Element, ? extends JsonNode> parentProcessor);

	public Context getContext() {
		return context;
	}

	public ELEMENT getElement() {
		return element;
	}

	public NODE getNode() {
		return node;
	}

	public String getExpression() {
		return expression;
	}

}
