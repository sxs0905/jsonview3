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
import org.developframework.jsonview.exception.JsonviewParseXmlException;
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
				final String alias = attributes.getValue("alias");
				final PropertyElement propertyElement = new PropertyElement(data, alias);
				((ContainerElement) stack.peek()).addElement(propertyElement);
			}
			break;
			case "ignore-property" : {
				final String data = attributes.getValue("data").trim();
				((ContainerElement) stack.peek()).addIgnoreProperty(data);
			}
			break;
			case "object" : {
				final String data = attributes.getValue("data").trim();
				final String alias = attributes.getValue("alias");
				final ObjectElement objectElement = new ObjectElement(data, alias);
				final String className = attributes.getValue("for-class");
				forClass(objectElement, className);
				((ContainerElement) stack.peek()).addElement(objectElement);
				stack.push(objectElement);
			}
			break;
			case "array" : {
				final String data = attributes.getValue("data").trim();
				final String alias = attributes.getValue("alias");
				final ArrayElement arrayElement = new ArrayElement(data, alias);
				final String className = attributes.getValue("for-class");
				forClass(arrayElement, className);
				((ContainerElement) stack.peek()).addElement(arrayElement);
				stack.push(arrayElement);
			}
			break;
			case "import" : {
				final String id = attributes.getValue("id").trim();
				String namespace = attributes.getValue("namespace");
				namespace = StringUtils.isNotBlank(namespace) ? namespace.trim() : tempJsonviewPackage.getNamespace();
				final ImportElement importElement = new ImportElement(namespace, id);
				((ContainerElement) stack.peek()).addElement(importElement);
			}
			break;
			case "jsonview" : {
				final String id = attributes.getValue("id").trim();
				final String data = attributes.getValue("data");
				final Jsonview jsonview = new Jsonview(id);
				if (StringUtils.isNotBlank(data)) {
					jsonview.setData(data.trim());
				}
				final String className = attributes.getValue("for-class");
				forClass(jsonview, className);
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
			case "object" :
			case "array" : {
				((ContainerElement) stack.pop()).loadClassProperty();
			}
			break;
			case "jsonview" : {
				Jsonview jsonview = (Jsonview) stack.pop();
				if (tempJsonviewPackage.containsKey(jsonview.getId())) {
					throw new ResourceNotUniqueException(String.format("Jsonview id \"%s\" already exists.", jsonview.getId()));
				}
				jsonview.loadClassProperty();
				tempJsonviewPackage.push(jsonview);
			}
			case "jsonview-package" : {
				configuration.addJsonviewPackage(tempJsonviewPackage);
			}
			break;
		}
	}

	public JsonviewConfiguration getConfiguration() {
		return configuration;
	}

	private void forClass(ContainerElement element, String className) {
		if (StringUtils.isNotBlank(className)) {
			try {
				element.setClazz(Class.forName(className));
			} catch (ClassNotFoundException e) {
				throw new JsonviewParseXmlException(String.format("Class \"%s\" is not found, please check configuration file.", className));
			}
		}
	}

}
