package org.developframework.jsonview.core.xml;

import java.util.Stack;

import org.developframework.jsonview.core.element.ArrayElement;
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
				ContainerElement containerElement = (ContainerElement) stack.peek();
				PropertyElement propertyElement = new PropertyElement(bind);
				containerElement.addElement(propertyElement);
			}
			break;
			case "object" : {
				String bind = attributes.getValue("bind").trim();
				ContainerElement containerElement = (ContainerElement) stack.peek();
				ObjectElement objectElement = new ObjectElement(bind);
				containerElement.addElement(objectElement);
				stack.push(objectElement);
			}
			break;
			case "array" : {
				String bind = attributes.getValue("bind").trim();
				ContainerElement containerElement = (ContainerElement) stack.peek();
				ArrayElement arrayElement = new ArrayElement(bind);
				containerElement.addElement(arrayElement);
				stack.push(arrayElement);
			}
			break;
			case "jsonview" : {
				String id = attributes.getValue("id").trim();
				Jsonview jsonview = new Jsonview(id);
				stack.push(jsonview);
			}
			break;
			case "jsonview-package" : {
				String namespace = attributes.getValue("namespace").trim();
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
			case "array" : {
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
