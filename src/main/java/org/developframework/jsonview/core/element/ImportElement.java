package org.developframework.jsonview.core.element;

import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.ImportProcessor;
import org.developframework.jsonview.core.processor.Processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ImportElement extends Element {

	private String namespace;
	private String id;

	public ImportElement(String namespace, String id) {
		super(null);
		this.namespace = namespace;
		this.id = id;
	}

	@Override
	public Processor<? extends Element, ? extends JsonNode> createProcessor(Context context, JsonNode jsonNode, String parentExpression) {
		return new ImportProcessor(context, this, (ObjectNode) jsonNode, parentExpression);
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
