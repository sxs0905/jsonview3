package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.JsonviewConfiguration;

import ognl.OgnlContext;

public class Context {

	private OgnlContext ognlContext;
	private JsonviewConfiguration jsonviewConfiguration;

	public OgnlContext getOgnlContext() {
		return ognlContext;
	}

	public void setOgnlContext(OgnlContext ognlContext) {
		this.ognlContext = ognlContext;
	}

	public JsonviewConfiguration getJsonviewConfiguration() {
		return jsonviewConfiguration;
	}

	public void setJsonviewConfiguration(JsonviewConfiguration jsonviewConfiguration) {
		this.jsonviewConfiguration = jsonviewConfiguration;
	}

}
