package org.developframework.jsonview.web.res;

import org.developframework.jsonview.data.DataModel;

public abstract class JsonviewResponse {

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
