package org.developframework.jsonview.core.processor;

import java.util.Objects;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 抽象处理器
 * 
 * @author qiuzhenhao
 *
 * @param <ELEMENT>
 * @param <NODE>
 */
public abstract class Processor<ELEMENT extends Element, NODE extends JsonNode> {

	// 上下文
	protected Context context;
	// 节点信息
	protected ELEMENT element;
	// 树节点
	protected NODE node;
	// 表达式
	protected Expression expression;

	public Processor(Context context, ELEMENT element, Expression parentExpression) {
		this.context = context;
		this.element = element;
		this.expression = createExpression(parentExpression);
	}

	/**
	 * 创建表达式
	 * 
	 * @param parentExpression
	 *            父节点表达式
	 * @return
	 */
	protected Expression createExpression(Expression parentExpression) {
		final String data = element.getData();
		if (Objects.nonNull(data) && data.startsWith("#")) {
			return Expression.buildObjectExpression(element.getData().substring(1));
		}
		return Expression.concatExpression(parentExpression, element.getData());
	}

	/**
	 * 处理子节点的操作
	 * 
	 * @param parentProcessor
	 *            父节点处理器对象
	 */
	protected abstract void process(Processor<? extends Element, ? extends JsonNode> parentProcessor);

	public Context getContext() {
		return context;
	}

	public ELEMENT getElement() {
		return element;
	}

	public void setNode(NODE node) {
		this.node = node;
	}

	public NODE getNode() {
		return node;
	}

	public Expression getExpression() {
		return expression;
	}

}
