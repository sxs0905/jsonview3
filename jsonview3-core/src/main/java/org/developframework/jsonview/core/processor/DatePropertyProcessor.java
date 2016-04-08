package org.developframework.jsonview.core.processor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.element.PropertyElement;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class DatePropertyProcessor extends PropertyProcessor<Object> {

	private DateFormat dateFormat;

	public DatePropertyProcessor(Context context, PropertyElement element, Expression parentExpression, String pattern) {
		super(context, element, parentExpression);
		dateFormat = new SimpleDateFormat(StringUtils.isBlank(pattern) ? "yyyy-MM-dd HH:mm:ss" : pattern);
	}

	@Override
	protected boolean support(Class<?> sourceClass) {
		return sourceClass == java.util.Date.class;
	}

	@Override
	protected void handle(ObjectNode parentNode, Object value, String showName) {
		parentNode.put(showName, dateFormat.format((java.util.Date) value));
	}

}
