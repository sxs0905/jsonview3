package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.Jsonview;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonviewProcessor extends ObjectProcessor {

	public JsonviewProcessor(Context context, Jsonview jsonview, ObjectNode node) {
		super(context, jsonview, node, jsonview.getData());
	}

	public JsonviewProcessor(Context context, Jsonview jsonview, ObjectNode node, String parentExpression, String data) {
		super(context, jsonview, node, parentExpression);
		jsonview.setData(data);
	}

	@Override
	protected String createExpression(String parentExpression) {
		return parentExpression;
	}

	@Override
	public void process(Processor<? extends Element, ? extends JsonNode> parentProcessor) {
		super.process(parentProcessor);
	}

}
