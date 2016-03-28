package org.developframework.jsonview.core.element;

public abstract class PropertyElement extends Element {

	protected Class<?> handler;

	public PropertyElement(String data, String alias) {
		super(data, alias);
	}

	public Class<?> getHandler() {
		return handler;
	}

	public void setHandler(Class<?> handler) {
		this.handler = handler;
	}
}
