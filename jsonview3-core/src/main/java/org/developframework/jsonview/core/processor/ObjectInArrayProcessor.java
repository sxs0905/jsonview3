package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.ObjectElement;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 数组内的对象类型节点处理器
 * 
 * @author qiuzhenhao
 *
 */
public class ObjectInArrayProcessor extends ObjectProcessor {

	protected int index;
	protected int size;

	public ObjectInArrayProcessor(Context context, ObjectElement element, String parentExpression, int index, int size) {
		super(context, element, parentExpression + "[" + index + "]");
		this.index = index;
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
			linkObjectProcessor.setIndex(index);
		}
	}

	@Override
	protected String createExpression(String parentExpression) {
		return parentExpression;
	}

	public int getIndex() {
		return index;
	}

	public int getSize() {
		return size;
	}

}
