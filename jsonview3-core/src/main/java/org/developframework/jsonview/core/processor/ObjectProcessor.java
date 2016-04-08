package org.developframework.jsonview.core.processor;

import java.util.Iterator;
import java.util.Optional;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.ObjectElement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 对象类型节点处理器
 * 
 * @author qiuzhenhao
 *
 */
public class ObjectProcessor extends ContainerProcessor<ObjectElement, ObjectNode> {

	public ObjectProcessor(Context context, ObjectElement element, String parentExpression) {
		super(context, element, parentExpression);
	}

	@Override
	public void process(Processor<? extends Element, ? extends JsonNode> parentProcessor) {
		for (Iterator<Element> iterator = element.elementIterator(); iterator.hasNext();) {
			final Element childElement = iterator.next();
			final Optional<Processor<? extends Element, ? extends JsonNode>> nextProcessorOptional = childElement.createProcessor(context, node, expression);
			nextProcessorOptional.ifPresent((nextProcessor) -> {
				// 扩展点：下一节点处理器的其他操作
				nextProcessorOtherOperate(nextProcessor);
				// 执行下一节点处理任务
				nextProcessor.process(this);
			});
		}
	}

	/**
	 * 扩展点：下一节点处理器的其他操作
	 * 
	 * @param nextProcessor
	 */
	protected void nextProcessorOtherOperate(Processor<? extends Element, ? extends JsonNode> nextProcessor) {
		if (nextProcessor instanceof MappingObjectProcessor) {
			MappingObjectProcessor mappingObjectProcessor = (MappingObjectProcessor) nextProcessor;
			mappingObjectProcessor.setParentArrayExpression(expression);
		}
	}
}
