package org.developframework.jsonview.core.xml;

import org.developframework.jsonview.core.element.LinkObjectElement;
import org.xml.sax.Attributes;

/**
 * 一对一链接型解析器
 * 
 * @author qiuzhenhao
 *
 */
public class LinkObjectElementSaxParser extends ContainerElementSaxParser<LinkObjectElement> {

	@Override
	public String qName() {
		return "link-object";
	}

	/**
	 * 实现：处理SAX节点开始时的操作
	 */
	@Override
	protected LinkObjectElement getElementInstance(String data, String alias) {
		return new LinkObjectElement(data, alias);
	}

	/**
	 * 实现：处理SAX节点关闭时的操作
	 */
	@Override
	protected void addOtherAttributes(LinkObjectElement element, Attributes attributes) {
		element.setNullHidden(attributes.getValue("null-hidden"));
		forClass(element, attributes);
	}

}
