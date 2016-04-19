package org.developframework.jsonview.core.element;

import java.util.Optional;

import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.Processor;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
