package org.developframework.jsonview.core.element;

import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.ObjectProcessor;
import org.developframework.jsonview.core.processor.Processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ObjectElement extends ContainerElement {

	public ObjectElement() {
		super(null);
	}

	public ObjectElement(String data) {
		super(data);
	}

	@Override
	public Processor<? extends Element, ? extends JsonNode> createProcessor(Context context, JsonNode jsonNode, String expression) {
		return new ObjectProcessor(context, this, (ObjectNode) jsonNode, expression);
	}

}
