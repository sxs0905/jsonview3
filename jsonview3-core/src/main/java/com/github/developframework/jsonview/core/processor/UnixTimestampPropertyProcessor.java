package com.github.developframework.jsonview.core.processor;

import java.util.Objects;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.developframework.jsonview.core.element.PropertyElement;
import com.github.developframework.jsonview.data.Expression;

/**
 * A processor for unixtimestamp property structure
 * 
 * @author qiuzhenhao
 *
 */
public class UnixTimestampPropertyProcessor extends DatePropertyProcessor {

	public UnixTimestampPropertyProcessor(Context context, PropertyElement element, Expression parentExpression) {
		super(context, element, parentExpression);
	}

	@Override
	protected void handle(ObjectNode parentNode, Class<?> clazz, Object value, String showName) {
		java.util.Date date = transformDate(clazz, value);
		if (Objects.isNull(date)) {
			parentNode.putNull(showName);
			return;
		}
		parentNode.put(showName, date.getTime() / 1000);
	}
}
