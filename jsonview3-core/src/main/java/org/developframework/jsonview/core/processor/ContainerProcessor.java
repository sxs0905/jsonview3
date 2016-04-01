package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.ContainerElement;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class ContainerProcessor<ELEMENT extends ContainerElement, NODE extends JsonNode> extends Processor<ELEMENT, NODE> {

	public ContainerProcessor(Context context, ELEMENT element, String parentExpression) {
		super(context, element, parentExpression);
	}

}
