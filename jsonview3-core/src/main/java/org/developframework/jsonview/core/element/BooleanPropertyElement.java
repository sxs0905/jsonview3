package org.developframework.jsonview.core.element;

import java.util.Optional;

import org.developframework.jsonview.core.processor.BooleanPropertyProcessor;
import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.Processor;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 布尔型属性节点
 * 
 * @author qiuzhenhao
 *
 */
public class BooleanPropertyElement extends PropertyElement {

	public BooleanPropertyElement(String data, String alias) {
		super(data, alias);
	}

	@Override
	public Optional<Processor<? extends Element, ? extends JsonNode>> createProcessor(Context context, ObjectNode parentNode, Expression parentExpression) {
		BooleanPropertyProcessor processor = new BooleanPropertyProcessor(context, this, parentExpression);
		processor.setNode(parentNode);
		return Optional.of(processor);
	}

}
