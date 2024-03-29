package com.github.developframework.jsonview.core.element;

import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.developframework.jsonview.core.processor.Context;
import com.github.developframework.jsonview.core.processor.ImportProcessor;
import com.github.developframework.jsonview.core.processor.Processor;
import com.github.developframework.jsonview.data.Expression;

/**
 * import
 * 
 * @author qiuzhenhao
 *
 */
public class ImportElement extends FunctionalElement {

	private String namespace;
	private String id;

	public ImportElement(String namespace, String id) {
		this.namespace = namespace;
		this.id = id;
	}

	@Override
	public Optional<Processor<? extends Element, ? extends JsonNode>> createProcessor(Context context, ObjectNode parentNode, Expression parentExpression) {
		ImportProcessor processor = new ImportProcessor(context, this, parentExpression);
		processor.setNode(parentNode);
		return Optional.of(processor);
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
