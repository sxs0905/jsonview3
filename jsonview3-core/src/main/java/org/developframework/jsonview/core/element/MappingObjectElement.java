package org.developframework.jsonview.core.element;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.MappingObjectProcessor;
import org.developframework.jsonview.core.processor.Processor;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MappingObjectElement extends ObjectElement {

	public enum MappingType {
		AUTO, SINPLE, MULTIPLE;

		public static MappingType parse(String typeStr) {
			if (StringUtils.isBlank(typeStr)) {
				return AUTO;
			} else {
				return MappingType.valueOf(typeStr.toUpperCase());
			}
		}
	}

	private String source;
	private String target;
	private ObjectElement childObjectElement;
	private ArrayElement childArrayElement;
	private MappingType mappingType;

	public MappingObjectElement(String data, String alias) {
		super(data, alias);
		childObjectElement = new ObjectElement(data, alias);
		childArrayElement = new ArrayElement(data, alias);
	}

	@Override
	public void addElement(Element element) {
		super.addElement(element);
		this.childObjectElement.addElement(element);
		this.childArrayElement.addElement(element);
	}

	@Override
	public Optional<Processor<? extends Element, ? extends JsonNode>> createProcessor(Context context, ObjectNode parentNode, Expression parentExpression) {
		MappingObjectProcessor processor = new MappingObjectProcessor(context, this, parentExpression);
		Optional<Object> optional = context.getDataModel().getData(processor.getExpression());
		if (optional.isPresent()) {
			return Optional.of(processor);
		}
		if (!nullHidden) {
			parentNode.putNull(this.showName());
		}
		return Optional.empty();
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public MappingType getMappingType() {
		return mappingType;
	}

	public void setMappingType(MappingType mappingType) {
		this.mappingType = mappingType;
	}

	public ObjectElement getChildObjectElement() {
		return childObjectElement;
	}

	public ArrayElement getChildArrayElement() {
		return childArrayElement;
	}

}
