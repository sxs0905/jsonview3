package org.developframework.jsonview.core.element;

import java.util.Optional;

import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.DatePropertyProcessor;
import org.developframework.jsonview.core.processor.Processor;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 时间日期型属性节点
 * 
 * @author qiuzhenhao
 *
 */
public class DatePropertyElement extends PropertyElement {

	// 模板
	private String pattern;

	public DatePropertyElement(String data, String alias) {
		super(data, alias);
	}

	/**
	 * 实现：创建处理器
	 */
	@Override
	public Optional<Processor<? extends Element, ? extends JsonNode>> createProcessor(Context context, ObjectNode parentNode, Expression parentExpression) {
		DatePropertyProcessor processor = new DatePropertyProcessor(context, this, parentExpression, pattern);
		processor.setNode(parentNode);
		return Optional.of(processor);
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
