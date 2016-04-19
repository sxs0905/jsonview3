package org.developframework.jsonview.core.processor;

import java.util.Iterator;
import java.util.Optional;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.ObjectElement;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * A processor for object structure
 * 
 * @author qiuzhenhao
 *
 */
public class ObjectProcessor extends ContainerProcessor<ObjectElement, ObjectNode> {

	public ObjectProcessor(Context context, ObjectElement element, Expression parentExpression) {
		super(context, element, parentExpression);
	}

	@Override
	public void process(DescribeContentProcessor<? extends Element, ? extends JsonNode> parentProcessor) {
		for (Iterator<Element> iterator = element.elementIterator(); iterator.hasNext();) {
			final Element childElement = iterator.next();
			final Optional<Processor<? extends Element, ? extends JsonNode>> nextProcessorOptional = childElement.createProcessor(context, node, expression);
			nextProcessorOptional.ifPresent((nextProcessor) -> {
				// extension: other operations in the next element processor
				nextProcessorOtherOperate(nextProcessor);
				// execute next element processor
				nextProcessor.process(this);
			});
		}
	}

	/**
	 * extension: other operations in the next element processor
	 * 
	 * @param nextProcessor next element processor
	 */
	protected void nextProcessorOtherOperate(Processor<? extends Element, ? extends JsonNode> nextProcessor) {
		if (nextProcessor instanceof MappingObjectProcessor) {
			MappingObjectProcessor mappingObjectProcessor = (MappingObjectProcessor) nextProcessor;
			mappingObjectProcessor.setParentArrayExpression(expression);
		}
	}
}
