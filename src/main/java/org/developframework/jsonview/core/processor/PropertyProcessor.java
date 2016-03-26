package org.developframework.jsonview.core.processor;

import java.math.BigDecimal;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.PropertyElement;
import org.developframework.jsonview.data.DataModel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class PropertyProcessor extends Processor<PropertyElement, JsonNode> {

	public PropertyProcessor(Context context, PropertyElement element) {
		super(context, element, null);
	}

	@Override
	protected void process(Processor<? extends Element, ? extends JsonNode> parentNodeProcessor) {
		DataModel dataModel = parentNodeProcessor.getContext().getDataModel();
		Object value = dataModel.getData(this.element.getExpression());
		ObjectNode parentNode = (ObjectNode) parentNodeProcessor.getNode();
		if (value == null) {
			parentNode.putNull(super.element.showName());
		} else if (value instanceof Integer) {
			parentNode.put(super.element.showName(), (Integer) value);
		} else if (value instanceof Long) {
			parentNode.put(super.element.showName(), (Long) value);
		} else if (value instanceof Boolean) {
			parentNode.put(super.element.showName(), (Boolean) value);
		} else if (value instanceof Float) {
			parentNode.put(super.element.showName(), (Float) value);
		} else if (value instanceof Double) {
			parentNode.put(super.element.showName(), (Double) value);
		} else if (value instanceof BigDecimal) {
			parentNode.put(super.element.showName(), (BigDecimal) value);
		} else {
			parentNode.put(super.element.showName(), value.toString());
		}
	}

}
