package org.developframework.jsonview.core.processor;

import java.math.BigDecimal;
import java.util.Objects;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.PropertyElement;
import org.developframework.jsonview.data.DataModel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class PropertyProcessor extends Processor<PropertyElement, JsonNode> {

	public PropertyProcessor(Context context, PropertyElement element, String parentExpression) {
		super(context, element, null, parentExpression);
	}

	@Override
	protected void process(Processor<? extends Element, ? extends JsonNode> parentNodeProcessor) {
		DataModel dataModel = parentNodeProcessor.getContext().getDataModel();
		Object value = dataModel.getData(expression);
		ObjectNode parentNode = (ObjectNode) parentNodeProcessor.getNode();
		bindValue(value, parentNode);
	}

	private void bindValue(Object value, ObjectNode parentNode) {
		final String showName = super.element.showName();
		if (Objects.isNull(value)) {
			parentNode.putNull(showName);
		} else if (value instanceof String) {
			parentNode.put(showName, (String) value);
		} else if (value instanceof Integer) {
			parentNode.put(showName, (Integer) value);
		} else if (value instanceof Long) {
			parentNode.put(showName, (Long) value);
		} else if (value instanceof Boolean) {
			parentNode.put(showName, (Boolean) value);
		} else if (value instanceof Float) {
			parentNode.put(showName, (Float) value);
		} else if (value instanceof Double) {
			parentNode.put(showName, (Double) value);
		} else if (value instanceof BigDecimal) {
			parentNode.put(showName, (BigDecimal) value);
		} else {
			parentNode.put(showName, value.toString());
		}
	}

}
