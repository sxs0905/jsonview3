package org.developframework.jsonview.core.element;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.Processor;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class Element {

	protected String data;
	protected String alias;

	public Element(String data) {
		this.data = data;
	}

	public abstract Processor<? extends Element, ? extends JsonNode> createProcessor(Context context, JsonNode jsonNode, String parentExpression);

	public String showName() {
		if (StringUtils.isNoneBlank(alias)) {
			return alias;
		}
		final int dotLastIndex = data.lastIndexOf(".");
		return dotLastIndex == -1 ? data : data.substring(dotLastIndex + 1);
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
