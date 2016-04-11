package org.developframework.jsonview.core.xml;

import org.xml.sax.Attributes;

/**
 * 节点解析器
 * 
 * @author qiuzhenhao
 *
 */
interface ElementSaxHandler {

	/**
	 * 获取节点qName
	 * 
	 * @return
	 */
	public String qName();

	/**
	 * 处理SAX节点开始时的操作
	 * 
	 * @param context
	 * @param attributes
	 */
	public void handleAtStartElement(ParserContext context, Attributes attributes);

	/**
	 * 处理SAX节点关闭时的操作
	 * 
	 * @param context
	 */
	public void handleAtEndElement(ParserContext context);

}
