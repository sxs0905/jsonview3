package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class ContainerProcessor<ELEMENT extends ContainerElement, NODE extends JsonNode> extends Processor<ELEMENT, NODE> {

	public ContainerProcessor(Context context, ELEMENT element, Expression parentExpression) {
		super(context, element, parentExpression);
	}

}
