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
public class NormalPropertyProcessor extends PropertyProcessor {

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
	protected void handle(ObjectNode parentNode, Class<?> clazz, Object value, String showName) {
		if (clazz == String.class) {
			parentNode.put(showName, (String) value);
		} else if (clazz == Integer.class) {
			parentNode.put(showName, (Integer) value);
		} else if (clazz == Long.class) {
			parentNode.put(showName, (Long) value);
		} else if (clazz == Boolean.class) {
			parentNode.put(showName, (Boolean) value);
		} else if (clazz == Float.class) {
			parentNode.put(showName, (Float) value);
		} else if (clazz == Double.class) {
			parentNode.put(showName, (Double) value);
		} else if (clazz == BigDecimal.class) {
			parentNode.put(showName, (BigDecimal) value);
		} else {
			parentNode.put(showName, value.toString());
		}
	}

}
