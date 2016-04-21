package com.github.developframework.jsonview.springmvc.res;

import java.io.Serializable;

import com.github.developframework.jsonview.data.DataModel;

/**
 * abstract jsonview response
 * 
 * @author qiuzhenhao
 *
 */
public abstract class JsonviewResponse implements Serializable {

	private static final long serialVersionUID = 5333224320840354160L;
	protected String namespace;
	protected String jsonviewId;
	protected DataModel dataModel;

	public JsonviewResponse(String namespace, String jsonviewId, DataModel dataModel) {
		this.namespace = namespace;
		this.jsonviewId = jsonviewId;
		this.dataModel = dataModel;
	}

	public String getNamespace() {
		return namespace;
	}

	public String getJsonviewId() {
		return jsonviewId;
	}

	public DataModel getDataModel() {
		return dataModel;
	}

	/**
	 * push data
	 * 
	 * @param dataName data name
	 * @param data data value
	 * @return this jsonviewResponse
	 */
	public JsonviewResponse putData(String dataName, Object data) {
		dataModel.putData(dataName, data);
		return this;
	}

}
