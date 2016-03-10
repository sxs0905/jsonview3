package org.developframework.jsonview.core.xml;

import java.util.Stack;

import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.Jsonview;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.element.JsonviewPackage;
import org.developframework.jsonview.core.element.ObjectElement;
import org.developframework.jsonview.core.element.PropertyElement;
import org.developframework.jsonview.exception.ResourceNotUniqueException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class JsonviewConfigurationXMLParseHandler extends DefaultHandler {

	private JsonviewConfiguration configuration;
	private JsonviewPackage tempJsonviewPackage;
	private Stack<Element> stack;

	public JsonviewConfigurationXMLParseHandler(JsonviewConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		stack = new Stack<>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		switch (qName) {
			case "property" : {
				String bind = attributes.getValue("bind").trim();
				PropertyElement propertyElement = new PropertyElement(bind);
				if (stack.peek() instanceof ContainerElement) {
					((ContainerElement) stack.peek()).addElement(propertyElement);
				}
			}
			break;
			case "object" : {
				String bind = attributes.getValue("bind").trim();
				ObjectElement objectElement = new ObjectElement(bind);
				if (stack.peek() instanceof ContainerElement) {
					((ContainerElement) stack.peek()).addElement(objectElement);
				}
				stack.push(objectElement);
			}
			break;
			case "jsonview" : {
				String id = attributes.getValue("id").trim();
				Jsonview jsonview = new Jsonview(id);
				stack.push(jsonview);
			}
			break;
			case "jsonview-package" : {
				String namespace = attributes.getValue("namespace");
				tempJsonviewPackage = new JsonviewPackage(namespace);
			}
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		switch (qName) {
			case "object" : {
				stack.pop();
			}
			break;
			case "jsonview" : {
				Jsonview jsonview = (Jsonview) stack.pop();
				if (tempJsonviewPackage.containsKey(jsonview.getId())) {
					throw new ResourceNotUniqueException(String.format("Jsonview id \"%s\" already exists.", jsonview.getId()));
				}
				tempJsonviewPackage.push(jsonview);
			}
			break;
			case "jsonview-package" : {
				configuration.addJsonviewPackage(tempJsonviewPackage);
			}
			break;
		}
	}

	public JsonviewConfiguration getConfiguration() {
		return configuration;
	}

}
