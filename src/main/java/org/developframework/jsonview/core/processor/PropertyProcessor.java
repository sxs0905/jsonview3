package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.PropertyElement;

import com.fasterxml.jackson.databind.JsonNode;

public class PropertyProcessor extends Processor<PropertyElement, JsonNode> {

	public PropertyProcessor(Context context, PropertyElement element) {
		super(context, element, null);
	}

	@Override
	protected void process(Processor<? extends Element, ? extends JsonNode> parentProcessor) {

		System.out.println("property");
	}

}
