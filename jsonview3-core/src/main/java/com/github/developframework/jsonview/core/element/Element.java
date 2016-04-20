package com.github.developframework.jsonview.core.element;

import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.developframework.jsonview.core.processor.Context;
import com.github.developframework.jsonview.core.processor.Processor;
import com.github.developframework.jsonview.data.Expression;

/**
 * abstract element
 * 
 * @author qiuzhenhao
 *
 */
public abstract class Element {

	/**
	 * Create the processor for the element
	 * 
	 * @param context processor's context
	 * @param parentNode parent node
	 * @param parentExpression parent expression
	 * @return processor's optional instance
	 */
	public abstract Optional<Processor<? extends Element, ? extends JsonNode>> createProcessor(Context context, ObjectNode parentNode, Expression parentExpression);

}
