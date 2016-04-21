package com.github.developframework.jsonview.core.element;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.github.developframework.jsonview.core.converter.PropertyConverter;
import com.github.developframework.jsonview.exception.JsonviewExpressionException;

/**
 * The basic property element
 * 
 * @author qiuhenhao
 *
 */
public abstract class PropertyElement extends DescribeContentElement {

	protected PropertyConverter<?> converter;

	public PropertyElement(String data, String alias) {
		super(data, alias);
	}

	public Optional<PropertyConverter<?>> getConverter() {
		return Optional.ofNullable(converter);
	}

	public void setConverter(String converter) {
		if (StringUtils.isBlank(converter)) {
			return;
		}
		try {
			Class<?> clazz = Class.forName(converter);
			this.converter = (PropertyConverter<?>) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			throw new JsonviewExpressionException(e.getMessage() + " is not found.");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
