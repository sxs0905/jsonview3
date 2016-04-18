package org.developframework.jsonview.core.processor;

import java.util.Optional;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.Jsonview;
import org.developframework.jsonview.core.element.Jsonview.Extend;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * jsonview处理器
 * 
 * @author qiuzhenhao
 *
 */
public class JsonviewProcessor extends ObjectProcessor {

	public JsonviewProcessor(Context context, Jsonview jsonview) {
		super(context, jsonview, Expression.buildObjectExpression(jsonview.getData()));
	}

	public JsonviewProcessor(Context context, Jsonview jsonview, ObjectNode node, Expression parentExpression, String data) {
		super(context, jsonview, parentExpression);
		jsonview.setData(data);
		this.node = node;
	}

	@Override
	protected Expression createExpression(Expression parentExpression) {
		return parentExpression;
	}

	/**
	 * @since 3.1.0
	 */
	@Override
	public void process(Processor<? extends Element, ? extends JsonNode> parentProcessor) {
		// 先处理继承的jsonview
		Optional<Extend> extendOptional = ((Jsonview) element).getExtend();
		if (extendOptional.isPresent()) {
			Extend extend = extendOptional.get();
			// 提取父jsonview
			Jsonview extendJsonview = context.getJsonviewConfiguration().extractJsonview(extend.getNamesapce(), extend.getJsonviewId());
			// 定义继承端口需要执行的回调函数
			final ExtendPortProcessor.ExtendCallback callback = parentProcessorInCallback -> super.process(parentProcessorInCallback);
			context.pushExtendCallback(extend.getPort(), callback);
			extendJsonview.createProcessor(context, node, expression).ifPresent(processor -> processor.process(parentProcessor));
		} else {
			super.process(parentProcessor);
		}
	}
}
