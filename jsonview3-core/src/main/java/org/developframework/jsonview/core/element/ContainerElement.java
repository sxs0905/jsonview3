package org.developframework.jsonview.core.element;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * container element
 * 
 * @author qiuzhenhao
 *
 */
public abstract class ContainerElement extends DescribeContentElement {

	// The child elements set
	protected List<Element> childElements = new ArrayList<>();
	// for-class class
	protected Class<?> clazz;
	// List of property names are ignored
	protected List<String> ignorePropertyNames = new ArrayList<>();

	public ContainerElement(String data, String alias) {
		super(data, alias);
	}

	/**
	 * add child element
	 * 
	 * @param element child element
	 */
	public void addChildElement(Element element) {
		childElements.add(element);
	}

	/**
	 * add ignore property
	 * 
	 * @param propertyName ignore property name
	 */
	public void addIgnoreProperty(String propertyName) {
		ignorePropertyNames.add(propertyName);
	}

	/**
	 * Determine whether to ignore the property field
	 * 
	 * @param field field
	 * @return boolean
	 */
	public boolean isIgnore(Field field) {
		return ignorePropertyNames.contains(field.getName());
	}

	/**
	 * Load classes all properties
	 */
	public void loadClassProperty() {
		if (Objects.nonNull(clazz)) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (!ignorePropertyNames.contains(field.getName())) {
					PropertyElement propertyElement = new NormalPropertyElement(field.getName(), null);
					if (!childElements.contains(propertyElement)) {
						addChildElement(propertyElement);
					}
				}
			}
		}
	}

	/**
	 * Copy other child of a container element set to self
	 * 
	 * @param containerElement container element
	 */
	public void copyChildElement(ContainerElement containerElement) {
		this.childElements.addAll(containerElement.childElements);
	}

	/**
	 * Return the element set of iterators
	 * 
	 * @return the element set of iterators
	 */
	public Iterator<Element> elementIterator() {
		return childElements.iterator();
	}

	/**
	 * Determine whether a child element set to an empty set
	 * 
	 * @return boolean
	 */
	public boolean isChildEmpty() {
		return childElements.isEmpty();
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

}
