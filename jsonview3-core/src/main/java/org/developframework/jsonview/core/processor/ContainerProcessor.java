package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.ContainerElement;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 容器处理器
 * 
 * @author qiuzhenhao
 *
 * @param <ELEMENT>
 * @param <NODE>
 */
public abstract class ContainerProcessor<ELEMENT extends ContainerElement, NODE extends JsonNode> extends Processor<ELEMENT, NODE> {

	public ContainerProcessor(Context context, ELEMENT element, Expression parentExpression) {
		super(context, element, parentExpression);
	}

}
