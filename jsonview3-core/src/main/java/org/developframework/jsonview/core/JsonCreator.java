package org.developframework.jsonview.core;

import org.developframework.jsonview.data.DataModel;

import com.fasterxml.jackson.core.JsonGenerator;

public interface JsonCreator {

	/**
	 * 创建json字符串
	 * 
	 * @param dataModel
	 * @param namespace
	 * @param id
	 * @return
	 */
	public String createJson(DataModel dataModel, String namespace, String id);

	/**
	 * 创建json字符串
	 * 
	 * @param dataModel
	 * @param namespace
	 * @param id
	 * @param isPretty
	 *            true时美化json
	 * @return
	 */
	public String createJson(DataModel dataModel, String namespace, String id, boolean isPretty);

	/**
	 * 向JsonGenerator输出json
	 * 
	 * @param generator
	 * @param dataModel
	 * @param namespace
	 * @param id
	 */
	public void printJson(JsonGenerator generator, DataModel dataModel, String namespace, String id);
}
