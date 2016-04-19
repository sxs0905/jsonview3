package org.developframework.jsonview.core.xml;

import org.xml.sax.Attributes;

/**
 * 节点解析器
 * 
 * @author qiuzhenhao
 *
 */
interface ElementSaxParser {

	/**
	 * 获取节点qName
	 * 
	 * @return IOException
	 */
	public String qName();

	/**
	 * 处理SAX节点开始时的操作
	 * 
	 * @param context 上下文
	 * @param attributes 属性集
	 */
	public void handleAtStartElement(ParserContext context, Attributes attributes);

	/**
	 * 处理SAX节点关闭时的操作
	 * 
	 * @param context 上下文
	 */
	public void handleAtEndElement(ParserContext context);

}
