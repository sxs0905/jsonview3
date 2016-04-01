package org.developframework.jsonview.core.element;

import java.util.Optional;

import org.developframework.jsonview.core.processor.ArrayProcessor;
import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.Processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ArrayElement extends ContainerElement {

	private ObjectElement childObjectElement;

	public ArrayElement(String bind, String alias) {
		super(bind, alias);
		this.childObjectElement = new ObjectElement(bind, alias);
	}

	@Override
	public Optional<Processor<? extends Element, ? extends JsonNode>> createProcessor(Context context, ObjectNode parentNode, String parentExpression) {
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
	public void addElement(Element element) {
		super.addElement(element);
		this.childObjectElement.addElement(element);
	}

	public ObjectElement getChildObjectElement() {
		return childObjectElement;
	}

}
