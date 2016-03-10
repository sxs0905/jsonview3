package org.developframework.jsonview.core.element;

public class PropertyElement extends Element {

	private Class<?> handler;

	public PropertyElement(String bind) {
		super(bind);
	}

	public Class<?> getHandler() {
		return handler;
	}

	public void setHandler(Class<?> handler) {
		this.handler = handler;
	}

}
