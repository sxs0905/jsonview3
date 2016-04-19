package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.ExtendPortElement;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * A processor for extend port structure
 * 
 * @author qiuzhenhao
 */
public class ExtendPortProcessor extends FunctionalProcessor<ExtendPortElement, JsonNode> {

	public ExtendPortProcessor(Context context, ExtendPortElement element, Expression parentExpression) {
		super(context, element, parentExpression);
	}

	@Override
	protected void process(DescribeContentProcessor<? extends Element, ? extends JsonNode> parentProcessor) {
		context.getExtendCallback(element.getPortName()).ifPresent(extendCallback -> extendCallback.call(parentProcessor));
	}

	/**
	 * extend callback interface
	 * 
	 * @author qiuzhenhao
	 */
	public interface ExtendCallback {

		public void call(DescribeContentProcessor<? extends Element, ? extends JsonNode> parentProcessor);
	}

}
