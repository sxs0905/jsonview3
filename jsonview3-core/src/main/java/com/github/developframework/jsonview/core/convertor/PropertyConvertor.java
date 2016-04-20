package com.github.developframework.jsonview.core.convertor;
/**
 * Properties of the converter
 * 
 * @author qiuzhenhao
 *
 * @param <TARGET> target Type
 */
public interface PropertyConvertor<TARGET> {

	/**
	 * convert handle
	 * 
	 * @param source source
	 * @return target value after convert
	 */
	public TARGET convert(Object source);
}
