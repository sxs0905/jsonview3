package org.developframework.jsonview.core.processor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.element.PropertyElement;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 时间日期属性型节点处理器
 * 
 * @author qiuzhenhao
 *
 */
public class DatePropertyProcessor extends PropertyProcessor<Object> {

	private DateFormat dateFormat;

	public DatePropertyProcessor(Context context, PropertyElement element, Expression parentExpression, String pattern) {
		super(context, element, parentExpression);
		dateFormat = new SimpleDateFormat(StringUtils.isBlank(pattern) ? "yyyy-MM-dd HH:mm:ss" : pattern);
	}

	/**
	 * 实现扩展点：判断是否支持某一类型的值，这里为支持java.util.Date类型
	 */
	@Override
	protected boolean support(Class<?> sourceClass) {
		return sourceClass == java.util.Date.class;
	}

	/**
	 * 实现扩展点：在Json树状结构上构造Node
	 */
	@Override
	protected void handle(ObjectNode parentNode, Object value, String showName) {
		parentNode.put(showName, dateFormat.format((java.util.Date) value));
	}

}
