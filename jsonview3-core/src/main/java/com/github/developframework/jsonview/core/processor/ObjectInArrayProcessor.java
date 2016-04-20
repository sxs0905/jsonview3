package com.github.developframework.jsonview.core.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.developframework.jsonview.core.element.Element;
import com.github.developframework.jsonview.core.element.ObjectElement;
import com.github.developframework.jsonview.data.Expression;

/**
 * A processor for object in array structure
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
	 * Need handle LinkObjectProcessor and MappingObjectProcessor
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
