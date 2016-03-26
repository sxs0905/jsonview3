package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.Element;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class Processor<ELEMENT extends Element, NODE extends JsonNode> {

	protected Context context;
	protected ELEMENT element;
	protected NODE node;

	public Processor(Context context, ELEMENT element, NODE node) {
		this.context = context;
		this.element = element;
		this.node = node;
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

}
