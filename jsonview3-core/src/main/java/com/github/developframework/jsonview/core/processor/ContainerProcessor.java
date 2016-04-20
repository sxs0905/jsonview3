package com.github.developframework.jsonview.core.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.developframework.jsonview.core.element.ContainerElement;
import com.github.developframework.jsonview.data.Expression;

/**
 * A processor for container structure
 * 
 * @author qiuzhenhao
 *
 * @param <ELEMENT> XML element
 * @param <NODE> JsonNode
 */
public abstract class ContainerProcessor<ELEMENT extends ContainerElement, NODE extends JsonNode> extends DescribeContentProcessor<ELEMENT, NODE> {

	public ContainerProcessor(Context context, ELEMENT element, Expression parentExpression) {
		super(context, element, parentExpression);
	}

}
