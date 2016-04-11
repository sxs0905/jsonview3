package org.developframework.jsonview.core.xml;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.element.Jsonview;
import org.developframework.jsonview.exception.ResourceNotUniqueException;
import org.xml.sax.Attributes;

/**
 * jsonview节点解析器
 * 
 * @author qiuzhenhao
 *
 */
class JsonviewElementSaxHandler extends ContainerElementSaxHandler<Jsonview> {

	@Override
	public String qName() {
		return "jsonview";
	}

	/**
	 * 实现：处理SAX节点开始时的操作
	 */
	@Override
	public void handleAtStartElement(ParserContext context, Attributes attributes) {
		final String id = attributes.getValue("id").trim();
		final String data = attributes.getValue("data");
		final Jsonview jsonview = new Jsonview(context.getJsonviewPackage().getNamespace(), id);
		if (StringUtils.isNotBlank(data)) {
			jsonview.setData(data.trim());
		}
		forClass(jsonview, attributes);
		context.getStack().push(jsonview);
	}

	/**
	 * 实现：处理SAX节点关闭时的操作
	 */
	@Override
	public void handleAtEndElement(ParserContext context) {
		Jsonview jsonview = (Jsonview) context.getStack().pop();
		if (context.getJsonviewPackage().containsKey(jsonview.getId())) {
			throw new ResourceNotUniqueException(String.format("Jsonview id \"%s\" already exists.", jsonview.getId()));
		}
		jsonview.loadClassProperty();
		context.getJsonviewPackage().push(jsonview);
	}

	/**
	 * 实现： 获取节点实例
	 */
	@Override
	protected Jsonview getElementInstance(String data, String alias) {
		// no opration
		return null;
	}

	/**
	 * 实现：增加其它属性处理
	 */
	@Override
	protected void addOtherAttributes(Jsonview element, Attributes attributes) {
		// no opration
	}

}
