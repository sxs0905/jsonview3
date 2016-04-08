package org.developframework.jsonview.core.processor;

import java.util.Optional;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.PropertyElement;
import org.developframework.jsonview.data.DataModel;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class PropertyProcessor<T> extends Processor<PropertyElement, JsonNode> {

	public PropertyProcessor(Context context, PropertyElement element, Expression parentExpression) {
		super(context, element, parentExpression);
	}

	@Override
	protected final void process(Processor<? extends Element, ? extends JsonNode> parentNodeProcessor) {
		DataModel dataModel = parentNodeProcessor.getContext().getDataModel();
		Optional<Object> valueOptional = dataModel.getData(expression);
		ObjectNode parentNode = (ObjectNode) parentNodeProcessor.getNode();
		final String showName = super.element.showName();
		if (!valueOptional.isPresent()) {
			if (!element.isNullHidden()) {
				parentNode.putNull(showName);
			}
			return;
		}
		valueOptional.ifPresent(value -> {
			// 处理handler
			Optional<Object> optional = element.getHandler().map(handler -> handler.handle(value));
			final Object newValue = optional.orElse(value);
			Class<?> valueClass = newValue.getClass();
			if (support(valueClass)) {
				handle(parentNode, newValue, showName);
			}
		});

	}

	protected abstract boolean support(Class<?> sourceClass);

	protected abstract void handle(ObjectNode parentNode, Object value, String showName);

}
