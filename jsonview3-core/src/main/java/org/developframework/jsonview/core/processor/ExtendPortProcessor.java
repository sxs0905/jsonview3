package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.ExtendPortElement;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 继承端口处理器
 * 
 * @author qiuzhenhao
 * @since 3.1.0
 */
public class ExtendPortProcessor extends Processor<ExtendPortElement, JsonNode> {

	public ExtendPortProcessor(Context context, ExtendPortElement element, Expression parentExpression) {
		super(context, element, parentExpression);
	}

	@Override
	protected void process(Processor<? extends Element, ? extends JsonNode> parentProcessor) {
		ExtendCallback extendCallback = context.getExtendCallback(element.getPortName());
		extendCallback.call(parentProcessor);
	}

	public interface ExtendCallback {

		public void call(Processor<? extends Element, ? extends JsonNode> parentProcessor);
	}

}
