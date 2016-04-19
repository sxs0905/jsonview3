package org.developframework.jsonview.core.element;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.MappingObjectProcessor;
import org.developframework.jsonview.core.processor.Processor;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * mapping-object
 * 
 * @author qiuhenhao
 *
 */
public class MappingObjectElement extends ObjectElement {

	/**
	 * Mapping type enumeration
	 * 
	 * @author qiuzhenhao
	 *
	 */
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

	// Condition of the source
	private String source;
	// target
	private String target;
	// The child object element
	private ObjectElement childObjectElement;
	// The child array element
	private ArrayElement childArrayElement;
	// mapping type
	private MappingType mappingType;

	public MappingObjectElement(String data, String alias) {
		super(data, alias);
		childObjectElement = new ObjectElement(data, alias);
		childArrayElement = new ArrayElement(data, alias);
	}

	@Override
	public void addChildElement(Element element) {
		super.addChildElement(element);
		this.childObjectElement.addChildElement(element);
		this.childArrayElement.addChildElement(element);
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
