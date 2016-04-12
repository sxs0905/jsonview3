package org.developframework.jsonview.core.element;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.convertor.PropertyConvertor;
import org.developframework.jsonview.exception.JsonviewExpressionException;

/**
 * 基本属性节点
 * 
 * @author qiuhenhao
 *
 */
public abstract class PropertyElement extends Element {

	// 转换器
	protected PropertyConvertor<?> convertor;

	public PropertyElement(String data, String alias) {
		super(data, alias);
	}

	public Optional<PropertyConvertor<?>> getConvertor() {
		return Optional.ofNullable(convertor);
	}

	public void setConvertor(String convertor) {
		if (StringUtils.isBlank(convertor)) {
			return;
		}
		try {
			Class<?> clazz = Class.forName(convertor);
			this.convertor = (PropertyConvertor<?>) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			throw new JsonviewExpressionException(e.getMessage() + " is not found.");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
