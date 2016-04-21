package com.github.developframework.jsonview.core.converter;

/**
 * Properties of the converter
 * 
 * @author qiuzhenhao
 *
 * @param <TARGET> target Type
 */
public interface PropertyConverter<TARGET> {

	/**
	 * convert handle
	 * 
	 * @param source source
	 * @return target value after convert
	 */
	public TARGET convert(Object source);
}
