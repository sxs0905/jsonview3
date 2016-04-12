package org.developframework.jsonview.core.convertor;
/**
 * 属性转换器
 * 
 * @author qiuzhenhao
 *
 * @param <TARGET>
 */
public interface PropertyConvertor<TARGET> {

	public TARGET convert(Object source);
}
