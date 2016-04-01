package org.developframework.jsonview.core.element;

import java.util.Optional;

import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.ObjectProcessor;
import org.developframework.jsonview.core.processor.Processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ObjectElement extends ContainerElement {

	public ObjectElement() {
		super(null, null);
	}

	public ObjectElement(String data, String alias) {
		super(data, alias);
	}

	@Override
	public Optional<Processor<? extends Element, ? extends JsonNode>> createProcessor(Context context, ObjectNode parentNode, String parentExpression) {
		ObjectProcessor processor = new ObjectProcessor(context, this, parentExpression);
		Optional<Object> optional = context.getDataModel().getData(processor.getExpression());
		if (optional.isPresent()) {
			final ObjectNode objectNode = parentNode.putObject(this.showName());
			processor.setNode(objectNode);
			return Optional.of(processor);
		}
		if (!nullHidden) {
			parentNode.putNull(this.showName());
		}
		return Optional.empty();
	}

}
