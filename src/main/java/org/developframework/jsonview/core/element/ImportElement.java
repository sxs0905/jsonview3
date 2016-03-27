package org.developframework.jsonview.core.element;

import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.Processor;

import com.fasterxml.jackson.databind.JsonNode;

public class ImportElement extends Element {

	private String namespace;
	private String id;

	public ImportElement(String namespace, String id, String parentExpression) {
		super(parentExpression, null);
		this.namespace = namespace;
		this.id = id;
	}

	@Override
	protected String createExpression(String parentExpression, String bind) {
		return parentExpression;
	}

	@Override
	public Processor<? extends Element, ? extends JsonNode> createProcessor(Context context, JsonNode jsonNode) {
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

}
