package org.developframework.jsonview.core.element;

import org.developframework.jsonview.core.processor.ArrayProcessor;
import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.Processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class ArrayElement extends ContainerElement {

	public ArrayElement(String parentExpression, String bind) {
		super(parentExpression, bind);
	}

	@Override
	public Processor<? extends Element, ? extends JsonNode> createProcessor(Context context, JsonNode jsonNode) {
		return new ArrayProcessor(context, this, (ArrayNode) jsonNode);
	}
}
