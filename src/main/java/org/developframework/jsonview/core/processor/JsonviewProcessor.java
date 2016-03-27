package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.ObjectElement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonviewProcessor extends ObjectProcessor {

	public JsonviewProcessor(Context context, ObjectElement element, ObjectNode node) {
		super(context, element, node, null);
	}

	@Override
	public void process(Processor<? extends Element, ? extends JsonNode> parentProcessor) {
		super.process(parentProcessor);
	}

}
