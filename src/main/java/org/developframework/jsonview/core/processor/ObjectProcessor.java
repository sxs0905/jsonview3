package org.developframework.jsonview.core.processor;

import java.util.Iterator;

import org.developframework.jsonview.core.element.ArrayElement;
import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.ImportElement;
import org.developframework.jsonview.core.element.ObjectElement;
import org.developframework.jsonview.core.element.PropertyElement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ObjectProcessor extends ContainerProcessor<ObjectElement, ObjectNode> {

	public ObjectProcessor(Context context, ObjectElement element, ObjectNode node) {
		super(context, element, node);
	}

	@Override
	protected void process(Processor<? extends Element, ? extends JsonNode> parentProcessor) {
		for (Iterator<Element> iterator = super.element.elementIterator(); iterator.hasNext();) {
			Element childElement = iterator.next();
			Processor<? extends Element, ? extends JsonNode> nextProcessor = this.checkNextProcessor(super.element, childElement);
			nextProcessor.process(this);
		}
	}

	/**
	 * 检查下一个处理器
	 * 
	 * @param element
	 * @param childElement
	 * @return
	 */
	private Processor<? extends Element, ? extends JsonNode> checkNextProcessor(Element element, Element childElement) {
		JsonNode jsonNode = null;
		if (childElement instanceof PropertyElement) {

		} else if (childElement instanceof ObjectElement) {
			jsonNode = node.putObject(childElement.showName());
		} else if (childElement instanceof ArrayElement) {
			jsonNode = node.putArray(childElement.showName());
		} else if (childElement instanceof ImportElement) {

		}
		return childElement.createProcessor(context, jsonNode);
	}

}
