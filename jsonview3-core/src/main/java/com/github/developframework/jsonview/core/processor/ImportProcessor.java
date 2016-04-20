package com.github.developframework.jsonview.core.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.developframework.jsonview.core.element.Element;
import com.github.developframework.jsonview.core.element.ImportElement;
import com.github.developframework.jsonview.core.element.Jsonview;
import com.github.developframework.jsonview.core.element.JsonviewConfiguration;
import com.github.developframework.jsonview.data.Expression;

/**
 * A processor for import structure
 * 
 * @author qiuzhenhao
 *
 */
public class ImportProcessor extends FunctionalProcessor<ImportElement, ObjectNode> {

	public ImportProcessor(Context context, ImportElement element, Expression parentExpression) {
		super(context, element, parentExpression);
	}

	@Override
	protected void process(DescribeContentProcessor<? extends Element, ? extends JsonNode> parentProcessor) {
		JsonviewConfiguration jsonviewConfiguration = super.context.getJsonviewConfiguration();
		Jsonview jsonview = jsonviewConfiguration.extractJsonview(element.getNamespace(), element.getId());
		final String data = parentProcessor.getElement().getData();
		JsonviewProcessor jsonviewElementProcessor = new JsonviewProcessor(super.context, jsonview, node, expression, data);
		jsonviewElementProcessor.process(parentProcessor);
	}

}
