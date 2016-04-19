package org.developframework.jsonview.core.element;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * 容器节点
 * 
 * @author qiuzhenhao
 *
 */
public abstract class ContainerElement extends Element {

	// 子节点集
	protected List<Element> childElements = new ArrayList<>();
	// for-class指向的类
	protected Class<?> clazz;
	// 忽略的属性名称列表
	protected List<String> ignorePropertyNames = new ArrayList<>();

	public ContainerElement(String data, String alias) {
		super(data, alias);
	}

	/**
	 * 增加子节点
	 * 
	 * @param element 子节点
	 */
	public void addChildElement(Element element) {
		childElements.add(element);
	}

	/**
	 * 增加被忽略的属性名称
	 * 
	 * @param propertyName 被忽略的属性名称
	 */
	public void addIgnoreProperty(String propertyName) {
		ignorePropertyNames.add(propertyName);
	}

	/**
	 * 判断是否忽略该属性字段
	 * 
	 * @param field 字段
	 * @return boolean
	 */
	public boolean isIgnore(Field field) {
		return ignorePropertyNames.contains(field.getName());
	}

	/**
	 * 加载类所有属性
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
	 * 复制其它的容器节点的子节点集到自己
	 * 
	 * @param containerElement 容器节点
	 */
	public void copyChildElement(ContainerElement containerElement) {
		this.childElements.addAll(containerElement.childElements);
	}

	/**
	 * 返回子节点集的迭代器
	 * 
	 * @return 子节点集的迭代器
	 */
	public Iterator<Element> elementIterator() {
		return childElements.iterator();
	}

	/**
	 * 判断子节点集是否为空集
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
