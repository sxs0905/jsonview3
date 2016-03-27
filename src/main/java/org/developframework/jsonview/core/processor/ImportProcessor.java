package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.ImportElement;
import org.developframework.jsonview.core.element.Jsonview;
import org.developframework.jsonview.core.element.JsonviewConfiguration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ImportProcessor extends Processor<ImportElement, ObjectNode> {

	public ImportProcessor(Context context, ImportElement element, ObjectNode node, String parentExpression) {
		super(context, element, node, parentExpression);
	}

	@Override
	protected String createExpression(String parentExpression) {
		return parentExpression;
	}

	@Override
	protected void process(Processor<? extends Element, ? extends JsonNode> parentProcessor) {
		JsonviewConfiguration jsonviewConfiguration = super.context.getJsonviewConfiguration();
		Jsonview jsonview = jsonviewConfiguration.extractJsonview(element.getNamespace(), element.getId());
		final String data = parentProcessor.getElement().getData();
		JsonviewProcessor jsonviewElementProcessor = new JsonviewProcessor(super.context, jsonview, node, expression, data);
		jsonviewElementProcessor.process(parentProcessor);
	}

}