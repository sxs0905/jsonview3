package com.github.developframework.jsonview.core.element;

import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.developframework.jsonview.core.processor.ArrayProcessor;
import com.github.developframework.jsonview.core.processor.Context;
import com.github.developframework.jsonview.core.processor.Processor;
import com.github.developframework.jsonview.data.Expression;

/**
 * array element
 * 
 * @author qiuzhenhao
 *
 */
public class ArrayElement extends ContainerElement {

	private ObjectElement childObjectElement;

	public ArrayElement(String data, String alias) {
		super(data, alias);
		this.childObjectElement = new ObjectElement(data, alias);
	}

	@Override
	public Optional<Processor<? extends Element, ? extends JsonNode>> createProcessor(Context context, ObjectNode parentNode, Expression parentExpression) {
		ArrayProcessor processor = new ArrayProcessor(context, this, parentExpression);
		Optional<Object> optional = context.getDataModel().getData(processor.getExpression());
		if (optional.isPresent()) {
			final ArrayNode arrayNode = parentNode.putArray(this.showName());
			processor.setNode(arrayNode);
			return Optional.of(processor);
		}
		if (!nullHidden) {
			parentNode.putNull(this.showName());
		}
		return Optional.empty();
	}

	@Override
	public void addChildElement(Element element) {
		super.addChildElement(element);
		this.childObjectElement.addChildElement(element);
	}

	public ObjectElement getChildObjectElement() {
		return childObjectElement;
	}

}
