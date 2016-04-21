package com.github.developframework.jsonview.core.processor;

import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.developframework.jsonview.core.element.Element;
import com.github.developframework.jsonview.core.element.PropertyElement;
import com.github.developframework.jsonview.data.DataModel;
import com.github.developframework.jsonview.data.Expression;

/**
 * A processor for property Type structure
 * 
 * @author qiuzhenhao
 *
 */
public abstract class PropertyProcessor extends DescribeContentProcessor<PropertyElement, JsonNode> {

	public PropertyProcessor(Context context, PropertyElement element, Expression parentExpression) {
		super(context, element, parentExpression);
	}

	@Override
	protected final void process(DescribeContentProcessor<? extends Element, ? extends JsonNode> parentNodeProcessor) {
		DataModel dataModel = parentNodeProcessor.getContext().getDataModel();
		Optional<Object> valueOptional = dataModel.getData(expression);
		ObjectNode parentNode = (ObjectNode) parentNodeProcessor.getNode();
		final String showName = super.element.showName();
		if (!valueOptional.isPresent()) {
			// If the element support null hidden
			if (!element.isNullHidden()) {
				parentNode.putNull(showName);
			}
			return;
		}
		valueOptional.ifPresent(value -> {
			// Processing converter
			Optional<Object> optional = element.getConverter().map(converter -> converter.convert(value));
			final Object newValue = optional.orElse(value);
			Class<?> valueClass = newValue.getClass();
			if (support(valueClass)) {
				handle(parentNode, valueClass, newValue, showName);
			}
		});

	}

	/**
	 * Extension: Determine whether to support a certain type of value
	 * 
	 * @param sourceClass source class
	 * @return boolean
	 */
	protected abstract boolean support(Class<?> sourceClass);

	/**
	 * Extension: Constructing the Node in Json tree structure
	 * 
	 * @param parentNode parent jsonNode
	 * @param clazz vlass
	 * @param value value
	 * @param showName show name
	 */
	protected abstract void handle(ObjectNode parentNode, Class<?> clazz, Object value, String showName);

}
