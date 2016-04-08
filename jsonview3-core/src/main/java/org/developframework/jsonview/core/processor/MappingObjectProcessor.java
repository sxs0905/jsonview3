package org.developframework.jsonview.core.processor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.developframework.jsonview.core.element.ArrayElement;
import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.MappingObjectElement;
import org.developframework.jsonview.core.element.ObjectElement;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MappingObjectProcessor extends ObjectProcessor {

	private String target;
	private Object sourceValue;

	public MappingObjectProcessor(Context context, MappingObjectElement element, String parentExpression) {
		super(context, element, parentExpression);
		this.target = element.getTarget();
	}

	public void setParentArrayExpression(String parentArrayExpression) {
		MappingObjectElement mappingObjectElement = (MappingObjectElement) element;
		final String expression = parentArrayExpression + "." + mappingObjectElement.getSource();
		Optional<Object> valueOptional = context.getDataModel().getData(expression);
		valueOptional.ifPresent(v -> sourceValue = v);
	}

	@Override
	public void process(Processor<? extends Element, ? extends JsonNode> parentProcessor) {
		if (Objects.nonNull(sourceValue)) {
			Optional<List<Expression>> optional = context.getDataModel().getData(expression, target, sourceValue);
			optional.ifPresent(list -> {
				MappingObjectElement mappingObjectElement = (MappingObjectElement) element;
				switch (mappingObjectElement.getMappingType()) {
					case MULTIPLE : {
						arrayProcess(parentProcessor, list, mappingObjectElement);
					}
					break;
					case SINPLE : {
						objectProcess(parentProcessor, list, mappingObjectElement);
					}
					break;
					case AUTO : {
						if (list.size() == 1) {
							objectProcess(parentProcessor, list, mappingObjectElement);
						} else {
							arrayProcess(parentProcessor, list, mappingObjectElement);
						}
					}
					break;
				}
			});
		}
	}

	private void objectProcess(Processor<? extends Element, ? extends JsonNode> parentProcessor, List<Expression> list, MappingObjectElement mappingObjectElement) {
		ObjectElement objectElement = mappingObjectElement.getChildObjectElement();
		objectElement.setData(list.get(0).toString());
		objectElement.createProcessor(context, (ObjectNode) parentProcessor.getNode(), null).ifPresent(processor -> {
			processor.process(parentProcessor);
		});
	}

	private void arrayProcess(Processor<? extends Element, ? extends JsonNode> parentProcessor, List<Expression> list, MappingObjectElement mappingObjectElement) {
		ArrayElement arrayElement = mappingObjectElement.getChildArrayElement();
		arrayElement.createProcessor(context, (ObjectNode) parentProcessor.getNode(), null).ifPresent(processor -> {
			((ArrayProcessor) processor).process(parentProcessor, list);
		});
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Object getSourceValue() {
		return sourceValue;
	}

	public void setSourceValue(Object sourceValue) {
		this.sourceValue = sourceValue;
	}

}
