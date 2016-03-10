package org.developframework.jsonview.core.element;

import org.apache.commons.lang3.StringUtils;

public abstract class Element {

	protected String bind;
	protected String alias;

	public Element(String bind) {
		this.bind = bind;
	}

	public String showName() {
		if (StringUtils.isNoneBlank(alias)) {
			return alias;
		}
		return bind;
	}

}
