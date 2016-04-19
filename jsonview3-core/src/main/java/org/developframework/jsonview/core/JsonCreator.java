package org.developframework.jsonview.core;

import org.developframework.jsonview.data.DataModel;

import com.fasterxml.jackson.core.JsonGenerator;

public interface JsonCreator {

	/**
	 * 创建json字符串
	 * 
	 * @param dataModel 数据模型
	 * @param namespace 命名空间
	 * @param id jsonviewId
	 * @return json字符串
	 */
	public String createJson(DataModel dataModel, String namespace, String id);

	/**
	 * 创建json字符串
	 * 
	 * @param dataModel 数据模型
	 * @param namespace 命名空间
	 * @param id jsonviewId
	 * @param isPretty true时美化json
	 * @return json字符串
	 */
	public String createJson(DataModel dataModel, String namespace, String id, boolean isPretty);

	/**
	 * 向JsonGenerator输出json
	 * 
	 * @param generator 发生器
	 * @param dataModel 数据模型
	 * @param namespace 命名空间
	 * @param id jsonviewId
	 */
	public void printJson(JsonGenerator generator, DataModel dataModel, String namespace, String id);
}
