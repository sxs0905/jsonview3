package org.developframework.jsonview.core.handler;
/**
 * 属性转换器
 * 
 * @author Administrator
 *
 * @param <TARGET>
 */
public interface PropertyConvertor<TARGET> {

	public TARGET convert(Object source);
}
