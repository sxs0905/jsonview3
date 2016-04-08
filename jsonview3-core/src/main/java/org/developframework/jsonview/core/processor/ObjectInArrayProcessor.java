package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.ObjectElement;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 数组内的对象类型节点处理器
 * 
 * @author qiuzhenhao
 *
 */
public class ObjectInArrayProcessor extends ObjectProcessor {

	protected int size;

	public ObjectInArrayProcessor(Context context, ObjectElement element, Expression parentExpression, int size) {
		super(context, element, parentExpression);
		this.size = size;
	}

	/**
	 * 扩展： 需要处理LinkObjectProcessor和MappingObjectProcessor
	 */
	@Override
	protected void nextProcessorOtherOperate(Processor<? extends Element, ? extends JsonNode> nextProcessor) {
		super.nextProcessorOtherOperate(nextProcessor);
		if (nextProcessor instanceof LinkObjectProcessor) {
			LinkObjectProcessor linkObjectProcessor = (LinkObjectProcessor) nextProcessor;
			linkObjectProcessor.checkSize(size);
			linkObjectProcessor.setIndex(expression.getIndex());
		}
	}

	@Override
	protected Expression createExpression(Expression parentExpression) {
		return parentExpression;
	}

	public int getSize() {
		return size;
	}

}
