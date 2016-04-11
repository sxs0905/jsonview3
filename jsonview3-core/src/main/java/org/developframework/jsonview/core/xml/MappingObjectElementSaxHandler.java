package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.MappingObjectElement;
import org.developframework.jsonview.core.element.MappingObjectElement.MappingType;
import org.xml.sax.Attributes;

/**
 * 一对多映射型解析器
 * 
 * @author qiuzhenhao
 *
 */
public class MappingObjectElementSaxHandler extends ContainerElementSaxHandler<MappingObjectElement> {

	@Override
	public String qName() {
		return "mapping-object";
	}

	/**
	 * 实现：处理SAX节点开始时的操作
	 */
	@Override
	protected MappingObjectElement getElementInstance(String data, String alias) {
		return new MappingObjectElement(data, alias);
	}

	/**
	 * 实现：处理SAX节点关闭时的操作
	 */
	@Override
	protected void addOtherAttributes(MappingObjectElement element, Attributes attributes) {
		element.setSource(attributes.getValue("source"));
		element.setTarget(attributes.getValue("target"));
		element.setNullHidden(attributes.getValue("null-hidden"));
		element.setMappingType(MappingType.parse(attributes.getValue("type")));
		forClass(element, attributes);
	}

}
