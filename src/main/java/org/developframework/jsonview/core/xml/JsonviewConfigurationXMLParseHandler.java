package org.developframework.jsonview.core.xml;

import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.element.ArrayElement;
import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.ImportElement;
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
				final String data = attributes.getValue("data").trim();
				final ContainerElement containerElement = (ContainerElement) stack.peek();
				final PropertyElement propertyElement = new PropertyElement(data);
				containerElement.addElement(propertyElement);
			}
			break;
			case "object" : {
				final String data = attributes.getValue("data").trim();
				final ContainerElement containerElement = (ContainerElement) stack.peek();
				final ObjectElement objectElement = new ObjectElement(data);
				containerElement.addElement(objectElement);
				stack.push(objectElement);
			}
			break;
			case "array" : {
				final String data = attributes.getValue("data").trim();
				final ContainerElement containerElement = (ContainerElement) stack.peek();
				final ArrayElement arrayElement = new ArrayElement(data);
				containerElement.addElement(arrayElement);
				stack.push(arrayElement);
			}
			break;
			case "import" : {
				final String id = attributes.getValue("id").trim();
				String namespace = attributes.getValue("namespace");
				namespace = StringUtils.isNotBlank(namespace) ? namespace.trim() : tempJsonviewPackage.getNamespace();
				final ImportElement importElement = new ImportElement(namespace, id);
				final ContainerElement containerElement = (ContainerElement) stack.peek();
				containerElement.addElement(importElement);
			}
			break;
			case "jsonview" : {
				final String id = attributes.getValue("id").trim();
				Jsonview jsonview = new Jsonview(id);
				stack.push(jsonview);
			}
			break;
			case "jsonview-package" : {
				final String namespace = attributes.getValue("namespace").trim();
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
