package org.developframework.jsonview.core.element;

import java.util.Optional;

import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.ExtendPortProcessor;
import org.developframework.jsonview.core.processor.Processor;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * extend-port
 * 
 * @author qiuzhenhao
 * @since 3.1.0
 */
public class ExtendPortElement extends Element {

	private String portName;

	public ExtendPortElement(String portName) {
		super(null, null);
		this.portName = portName;
	}

	@Override
	public Optional<Processor<? extends Element, ? extends JsonNode>> createProcessor(Context context, ObjectNode parentNode, Expression parentExpression) {
		ExtendPortProcessor processor = new ExtendPortProcessor(context, this, parentExpression);
		processor.setNode(parentNode);
		return Optional.of(processor);
	}

	public String getPortName() {
		return portName;
	}

}
