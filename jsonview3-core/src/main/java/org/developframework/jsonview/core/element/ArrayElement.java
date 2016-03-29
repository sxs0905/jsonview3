package org.developframework.jsonview.core.element;

import org.developframework.jsonview.core.processor.ArrayProcessor;
import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.Processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class ArrayElement extends ContainerElement {

	private ObjectElement childObjectElement;

	public ArrayElement(String bind, String alias) {
		super(bind, alias);
		this.childObjectElement = new ObjectElement(bind, alias);
	}

	@Override
	public Processor<? extends Element, ? extends JsonNode> createProcessor(Context context, JsonNode jsonNode, String parentExpression) {
		return new ArrayProcessor(context, this, (ArrayNode) jsonNode, parentExpression);
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
