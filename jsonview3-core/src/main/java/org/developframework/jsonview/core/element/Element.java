package org.developframework.jsonview.core.element;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.Processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class Element {

	protected String data;
	protected String alias;
	protected boolean nullHidden;

	public Element(String data, String alias) {
		this.data = data;
		this.alias = alias;
	}

	public abstract Optional<Processor<? extends Element, ? extends JsonNode>> createProcessor(Context context, ObjectNode parentNode, String parentExpression);

	public String showName() {
		if (StringUtils.isNotBlank(alias)) {
			return alias;
		}
		String data = ignoreHeadSign(this.data);
		final int dotLastIndex = data.lastIndexOf(".");
		data = dotLastIndex == -1 ? data : data.substring(dotLastIndex + 1);
		return camelCaseToUnderline(data);
	}

	private final String ignoreHeadSign(String data) {
		if (data.startsWith("#")) {
			return data.substring(1);
		}
		return data;
	}

	private final String camelCaseToUnderline(String camelCaseStr) {
		final StringBuffer sb = new StringBuffer();
		for (int i = 0, size = camelCaseStr.length(); i < size; i++) {
			char ch = camelCaseStr.charAt(i);
			if (ch >= 'A' && ch <= 'Z') {
				if (i == 0) {
					sb.append((char) (ch + 32));
				} else {
					sb.append('_').append((char) (ch + 32));
				}
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
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

	public boolean isNullHidden() {
		return nullHidden;
	}

	public void setNullHidden(String nullHiddenStr) {
		this.nullHidden = StringUtils.isBlank(nullHiddenStr) ? false : new Boolean(nullHiddenStr).booleanValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Element) {
			return data.equals(((Element) obj).getData()) || this == obj;
		}
		return false;
	}

}
