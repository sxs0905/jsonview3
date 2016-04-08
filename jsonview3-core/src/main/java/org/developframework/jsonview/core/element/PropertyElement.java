package org.developframework.jsonview.core.element;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.handler.PropertyHandler;
import org.developframework.jsonview.exception.JsonviewExpressionException;

public abstract class PropertyElement extends Element {

	protected PropertyHandler<?> handler;

	public PropertyElement(String data, String alias) {
		super(data, alias);
	}

	public Optional<PropertyHandler<?>> getHandler() {
		return Optional.ofNullable(handler);
	}

	public void setHandler(String handler) {
		if (StringUtils.isBlank(handler)) {
			return;
		}
		try {
			Class<?> clazz = Class.forName(handler);
			this.handler = (PropertyHandler<?>) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			throw new JsonviewExpressionException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
