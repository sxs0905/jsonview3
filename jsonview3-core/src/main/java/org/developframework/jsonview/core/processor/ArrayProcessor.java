package org.developframework.jsonview.core.processor;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.developframework.jsonview.core.element.ArrayElement;
import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.data.DataModel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ArrayProcessor extends ContainerProcessor<ArrayElement, ArrayNode> {

	public ArrayProcessor(Context context, ArrayElement element, String parentExpression) {
		super(context, element, parentExpression);
	}

	@Override
	protected void process(Processor<? extends Element, ? extends JsonNode> parentProcessor) {
		final DataModel dataModel = parentProcessor.getContext().getDataModel();
		final Optional<Object> objOptional = dataModel.getData(expression);
		if (objOptional.isPresent()) {
			Object obj = objOptional.get();
			int size = 0;
			if (obj.getClass().isArray()) {
				size = ((Object[]) obj).length;
			} else if (obj instanceof Collection<?>) {
				size = ((Collection<?>) obj).size();
			}
			for (int i = 0; i < size; i++) {
				sinple(i);
			}
		}
	}

	private void sinple(int index) {
		if (element.isChildEmpty()) {
			empty(index);
		} else {
			final ObjectInArrayProcessor childProcessor = new ObjectInArrayProcessor(context, element.getChildObjectElement(), expression, index);
			final ObjectNode objectNode = super.context.getObjectMapper().createObjectNode();
			childProcessor.setNode(objectNode);
			childProcessor.process(null);
			node.add(objectNode);
		}
	}

	private void empty(int index) {
		final Object object = context.getDataModel().getData(expression + "[" + index + "]");
		if (Objects.isNull(object)) {
			node.addNull();
		} else if (object instanceof String) {
			node.add((String) object);
		} else if (object instanceof Integer) {
			node.add((Integer) object);
		} else if (object instanceof Long) {
			node.add((Long) object);
		} else if (object instanceof Boolean) {
			node.add((Boolean) object);
		} else if (object instanceof Float) {
			node.add((Float) object);
		} else if (object instanceof Double) {
			node.add((Double) object);
		} else if (object instanceof BigDecimal) {
			node.add((BigDecimal) object);
		} else {
			node.add(object.toString());
		}
	}

}
