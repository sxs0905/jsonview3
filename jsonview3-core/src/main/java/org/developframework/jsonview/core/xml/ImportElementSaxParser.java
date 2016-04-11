package org.developframework.jsonview.core.xml;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.core.element.ImportElement;
import org.xml.sax.Attributes;

/**
 * 导入节点解析器
 * 
 * @author qiuzhenhao
 *
 */
class ImportElementSaxParser implements ElementSaxParser {

	@Override
	public String qName() {
		return "import";
	}

	/**
	 * 实现：处理SAX节点开始时的操作
	 */
	@Override
	public void handleAtStartElement(ParserContext context, Attributes attributes) {
		final String id = attributes.getValue("id").trim();
		String namespace = attributes.getValue("namespace");
		namespace = StringUtils.isNotBlank(namespace) ? namespace.trim() : context.getJsonviewPackage().getNamespace();
		final ImportElement importElement = new ImportElement(namespace, id);
		((ContainerElement) context.getStack().peek()).addChildElement(importElement);
	}

	/**
	 * 实现：处理SAX节点关闭时的操作
	 */
	@Override
	public void handleAtEndElement(ParserContext context) {
		// no opration
	}

}
