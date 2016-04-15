package org.developframework.jsonview.springmvc.res;

import java.io.Serializable;

import org.developframework.jsonview.data.DataModel;

/**
 * 抽象Jsonview响应
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

	public JsonviewResponse putData(String dataName, Object data) {
		dataModel.putData(dataName, data);
		return this;
	}

}
