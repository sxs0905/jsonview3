package com.github.developframework.jsonview.core.processor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.developframework.jsonview.core.element.PropertyElement;
import com.github.developframework.jsonview.data.Expression;

/**
 * A processor for date property structure
 * 
 * @author qiuzhenhao
 *
 */
public class DatePropertyProcessor extends PropertyProcessor {

	private DateFormat dateFormat;
	// List of support class
	private static final List<Class<?>> ACCEPT_CLASS_LIST = new ArrayList<>(9);

	public DatePropertyProcessor(Context context, PropertyElement element, Expression parentExpression) {
		this(context, element, parentExpression, null);
	}

	public DatePropertyProcessor(Context context, PropertyElement element, Expression parentExpression, String pattern) {
		super(context, element, parentExpression);
		dateFormat = new SimpleDateFormat(StringUtils.isBlank(pattern) ? "yyyy-MM-dd HH:mm:ss" : pattern);
		ACCEPT_CLASS_LIST.add(java.util.Date.class);
		ACCEPT_CLASS_LIST.add(java.util.Calendar.class);
		ACCEPT_CLASS_LIST.add(java.sql.Date.class);
		ACCEPT_CLASS_LIST.add(java.sql.Time.class);
		ACCEPT_CLASS_LIST.add(java.sql.Timestamp.class);
		ACCEPT_CLASS_LIST.add(java.time.LocalDate.class);
		ACCEPT_CLASS_LIST.add(java.time.LocalDateTime.class);
		ACCEPT_CLASS_LIST.add(java.time.LocalTime.class);
		ACCEPT_CLASS_LIST.add(java.time.Instant.class);
	}

	@Override
	protected boolean support(Class<?> sourceClass) {
		return ACCEPT_CLASS_LIST.contains(sourceClass);
	}

	@Override
	protected void handle(ObjectNode parentNode, Class<?> clazz, Object value, String showName) {
		java.util.Date date = transformDate(clazz, value);
		if (Objects.isNull(date)) {
			parentNode.putNull(showName);
			return;
		}
		parentNode.put(showName, dateFormat.format(date));
	}

	protected java.util.Date transformDate(Class<?> clazz, Object value) {
		java.util.Date date = null;
		if (clazz == java.util.Date.class) {
			// java.util.Date
			date = (java.util.Date) value;
		} else if (clazz == java.sql.Date.class) {
			// java.util.Calendar
			date = ((java.util.Calendar) value).getTime();
		} else if (clazz == java.sql.Date.class) {
			// java.sql.Date
			date = new Date(((java.sql.Date) value).getTime());
		} else if (clazz == java.sql.Time.class) {
			// java.sql.Time
			date = new Date(((java.sql.Time) value).getTime());
		} else if (clazz == java.sql.Timestamp.class) {
			// java.sql.Timestamp
			date = new Date(((java.sql.Timestamp) value).getTime());
		} else if (clazz == LocalDateTime.class) {
			// LocalDateTime
			date = Date.from(((java.time.LocalDateTime) value).atZone(ZoneId.systemDefault()).toInstant());
		} else if (clazz == LocalDate.class) {
			// LocalDate
			LocalTime localTime = LocalTime.parse("00:00:00");
			LocalDateTime localDateTime = LocalDateTime.of((LocalDate) value, localTime);
			date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		} else if (clazz == LocalTime.class) {
			// LocalTime
			LocalDate localDate = LocalDate.now();
			LocalDateTime localDateTime = LocalDateTime.of(localDate, (java.time.LocalTime) value);
			date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		} else if (clazz == Instant.class) {
			// Instant
			date = Date.from((Instant) value);
		}
		return date;
	}
}
