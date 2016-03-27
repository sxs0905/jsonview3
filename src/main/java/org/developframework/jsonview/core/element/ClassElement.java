package org.developframework.jsonview.core.element;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ClassElement extends ObjectElement {

	private Class<?> clazz;
	private List<String> ignoreProperties = new ArrayList<>();

	public ClassElement(String data, Class<?> clazz) {
		super(data);
		this.clazz = clazz;
		loadProperty();
	}

	private void loadProperty() {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (!ignoreProperties.contains(field.getName())) {
				addElement(new PropertyElement(field.getName()));
			}
		}
	}

	public Class<?> getClazz() {
		return clazz;
	}

}
