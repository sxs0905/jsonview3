package org.developframework.jsonview.core.element;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.Processor;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class Element {

	protected String bind;
	protected String alias;

	public Element(String bind) {
		this.bind = bind;
	}

	public abstract Processor<? extends Element, ? extends JsonNode> createProcessor(Context context, JsonNode jsonNode, String parentExpression);

	public String showName() {
		if (StringUtils.isNoneBlank(alias)) {
			return alias;
		}
		return bind;
	}

	public String getBind() {
		return bind;
	}

	public void setBind(String bind) {
		this.bind = bind;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
