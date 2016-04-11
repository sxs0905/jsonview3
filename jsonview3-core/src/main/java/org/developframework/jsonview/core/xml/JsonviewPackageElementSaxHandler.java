package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.JsonviewPackage;
import org.xml.sax.Attributes;

/**
 * 包节点解析器
 * 
 * @author qiuzhenhao
 *
 */
class JsonviewPackageElementSaxHandler implements ElementSaxHandler {

	@Override
	public String qName() {
		return "jsonview-package";
	}

	/**
	 * 实现：处理SAX节点开始时的操作
	 */
	@Override
	public void handleAtStartElement(ParserContext context, Attributes attributes) {
		final String namespace = attributes.getValue("namespace").trim();
		context.setJsonviewPackage(new JsonviewPackage(namespace));
	}

	/**
	 * 实现：处理SAX节点关闭时的操作
	 */
	@Override
	public void handleAtEndElement(ParserContext context) {
		context.getConfiguration().addJsonviewPackage(context.getJsonviewPackage());
	}

}
