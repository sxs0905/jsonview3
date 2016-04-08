package org.developframework.jsonview.core.processor;

import java.math.BigDecimal;

import org.developframework.jsonview.core.element.PropertyElement;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 通用的属性型节点处理器
 * 
 * @author qiuzhenhao
 *
 */
public class NormalPropertyProcessor extends PropertyProcessor<Object> {

	public NormalPropertyProcessor(Context context, PropertyElement element, Expression parentExpression) {
		super(context, element, parentExpression);
	}

	/**
	 * 实现扩展点：判断是否支持某一类型的值，这里为始终支持
	 */
	@Override
	protected boolean support(Class<?> sourceClass) {
		return true;
	}

	/**
	 * 实现扩展点：在Json树状结构上构造Node
	 */
	@Override
	protected void handle(ObjectNode parentNode, Object value, String showName) {
		if (value instanceof String) {
			parentNode.put(showName, (String) value);
		} else if (value instanceof Integer) {
			parentNode.put(showName, (Integer) value);
		} else if (value instanceof Long) {
			parentNode.put(showName, (Long) value);
		} else if (value instanceof Boolean) {
			parentNode.put(showName, (Boolean) value);
		} else if (value instanceof Float) {
			parentNode.put(showName, (Float) value);
		} else if (value instanceof Double) {
			parentNode.put(showName, (Double) value);
		} else if (value instanceof BigDecimal) {
			parentNode.put(showName, (BigDecimal) value);
		} else {
			parentNode.put(showName, value.toString());
		}
	}

}
