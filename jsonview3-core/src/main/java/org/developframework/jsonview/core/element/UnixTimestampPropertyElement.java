package org.developframework.jsonview.core.element;

import java.util.Optional;

import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.Processor;
import org.developframework.jsonview.core.processor.UnixTimestampPropertyProcessor;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Unix时间戳型属性节点
 * 
 * @author qiuzhenhao
 *
 */
public class UnixTimestampPropertyElement extends PropertyElement {

	public UnixTimestampPropertyElement(String data, String alias) {
		super(data, alias);
	}

	/**
	 * 实现：创建处理器
	 */
	@Override
	public Optional<Processor<? extends Element, ? extends JsonNode>> createProcessor(Context context, ObjectNode parentNode, Expression parentExpression) {
		UnixTimestampPropertyProcessor processor = new UnixTimestampPropertyProcessor(context, this, parentExpression);
		processor.setNode(parentNode);
		return Optional.of(processor);
	}
}
