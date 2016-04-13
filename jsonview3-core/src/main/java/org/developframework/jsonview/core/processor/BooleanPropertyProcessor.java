package org.developframework.jsonview.core.processor;

import java.util.ArrayList;
import java.util.List;

import org.developframework.jsonview.core.element.PropertyElement;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 布尔型属性处理器
 * 
 * @author qiuzhenhao
 *
 */
public class BooleanPropertyProcessor extends PropertyProcessor {

	// 支持的类型列表
	private static final List<Class<?>> ACCEPT_CLASS_LIST = new ArrayList<>(8);

	public BooleanPropertyProcessor(Context context, PropertyElement element, Expression parentExpression) {
		super(context, element, parentExpression);
		ACCEPT_CLASS_LIST.add(boolean.class);
		ACCEPT_CLASS_LIST.add(Boolean.class);
		ACCEPT_CLASS_LIST.add(int.class);
		ACCEPT_CLASS_LIST.add(Integer.class);
		ACCEPT_CLASS_LIST.add(long.class);
		ACCEPT_CLASS_LIST.add(Long.class);
		ACCEPT_CLASS_LIST.add(short.class);
		ACCEPT_CLASS_LIST.add(Short.class);
	}

	/**
	 * 实现扩展点：判断是否支持某一类型的值
	 */
	@Override
	protected boolean support(Class<?> sourceClass) {
		return ACCEPT_CLASS_LIST.contains(sourceClass);
	}

	/**
	 * 实现扩展点：在Json树状结构上构造Node
	 */
	@Override
	protected void handle(ObjectNode parentNode, Class<?> clazz, Object value, String showName) {
		boolean v = false;
		if (clazz == Boolean.class) {
			v = ((Boolean) value).booleanValue();
		} else if (clazz == Integer.class) {
			v = ((Integer) value).intValue() != 0;
		} else if (clazz == Long.class) {
			v = ((Long) value).longValue() != 0;
		} else if (clazz == Short.class) {
			v = ((Short) value).shortValue() != 0;
		} else {
			parentNode.putNull(showName);
			return;
		}
		parentNode.put(showName, v);
	}

}
