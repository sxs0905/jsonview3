package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.Jsonview;
import org.developframework.jsonview.data.Expression;

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
}
