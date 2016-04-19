package org.developframework.jsonview.core.xml;

import java.util.Stack;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.element.JsonviewPackage;

/**
 * parser's context
 * 
 * @author qiuzhenhao
 *
 */
final class ParserContext {

	private JsonviewConfiguration configuration;
	// current jsonviewPackage
	private JsonviewPackage jsonviewPackage;
	// element stack
	private Stack<Element> stack;

	public ParserContext(JsonviewConfiguration configuration) {
		this.configuration = configuration;
		this.stack = new Stack<>();
	}

	public JsonviewConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(JsonviewConfiguration configuration) {
		this.configuration = configuration;
	}

	public JsonviewPackage getJsonviewPackage() {
		return jsonviewPackage;
	}

	public void setJsonviewPackage(JsonviewPackage jsonviewPackage) {
		this.jsonviewPackage = jsonviewPackage;
	}

	public Stack<Element> getStack() {
		return stack;
	}

	public void setStack(Stack<Element> stack) {
		this.stack = stack;
	}

}
