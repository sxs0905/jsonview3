package org.developframework.jsonview.core;

import org.developframework.jsonview.data.DataModel;

import com.fasterxml.jackson.core.JsonGenerator;

/**
 * json creator interface
 * 
 * @author qiuzhenhao
 *
 */
public interface JsonCreator {

	/**
	 * create json string
	 * 
	 * @param dataModel data model
	 * @param namespace namespace
	 * @param id jsonview id
	 * @return json string
	 */
	public String createJson(DataModel dataModel, String namespace, String id);

	/**
	 * create json string
	 * 
	 * @param dataModel data model
	 * @param namespace namespace
	 * @param id jsonview id
	 * @param isPretty pretty json when the value is true
	 * @return json string
	 */
	public String createJson(DataModel dataModel, String namespace, String id, boolean isPretty);

	/**
	 * to JsonGenerator output json
	 * 
	 * @param generator generator
	 * @param dataModel data model
	 * @param namespace namespace
	 * @param id jsonview id
	 */
	public void printJson(JsonGenerator generator, DataModel dataModel, String namespace, String id);
}
