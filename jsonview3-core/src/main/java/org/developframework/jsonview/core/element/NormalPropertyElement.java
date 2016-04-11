package org.developframework.jsonview.core.element;

import java.util.Optional;

import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.NormalPropertyProcessor;
import org.developframework.jsonview.core.processor.Processor;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 普通型属性节点
 * 
 * @author qiuhenhao
 *
 */
public class NormalPropertyElement extends PropertyElement {

	public NormalPropertyElement(String data, String alias) {
		super(data, alias);
	}

	/**
	 * 实现： 创建处理器
	 */
	@Override
	public Optional<Processor<? extends Element, ? extends JsonNode>> createProcessor(Context context, ObjectNode parentNode, Expression parentExpression) {
		NormalPropertyProcessor processor = new NormalPropertyProcessor(context, this, parentExpression);
		processor.setNode(parentNode);
		return Optional.of(processor);
	}
}
