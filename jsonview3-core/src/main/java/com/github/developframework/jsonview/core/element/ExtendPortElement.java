package com.github.developframework.jsonview.core.element;

import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.developframework.jsonview.core.processor.Context;
import com.github.developframework.jsonview.core.processor.ExtendPortProcessor;
import com.github.developframework.jsonview.core.processor.Processor;
import com.github.developframework.jsonview.data.Expression;

/**
 * extend-port
 * 
 * @author qiuzhenhao
 */
public class ExtendPortElement extends FunctionalElement {

	private String portName;

	public ExtendPortElement(String portName) {
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
