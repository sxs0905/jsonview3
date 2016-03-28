package org.developframework.jsonview.core.element;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public abstract class ContainerElement extends Element {

	protected List<Element> childElements = new ArrayList<>();
	protected Class<?> clazz;
	protected List<String> ignorePropertyNames = new ArrayList<>();

	public ContainerElement(String bind, String alias) {
		super(bind, alias);
	}

	public void addElement(Element element) {
		childElements.add(element);
	}

	public void addIgnoreProperty(String propertyName) {
		ignorePropertyNames.add(propertyName);
	}

	public boolean isIgnore(Field field) {
		return ignorePropertyNames.contains(field.getName());
	}

	public void loadClassProperty() {
		if (Objects.nonNull(clazz)) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (!ignorePropertyNames.contains(field.getName())) {
					PropertyElement propertyElement = new PropertyElement(field.getName(), null);
					if (!childElements.contains(propertyElement)) {
						addElement(propertyElement);
					}
				}
			}
		}
	}

	public Iterator<Element> elementIterator() {
		return childElements.iterator();
	}

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
