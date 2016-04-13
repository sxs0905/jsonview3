package org.developframework.jsonview.core.processor;

import java.util.Optional;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.PropertyElement;
import org.developframework.jsonview.data.DataModel;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 抽象的属性型节点处理器
 * 
 * @author qiuzhenhao
 *
 * @param <T>
 */
public abstract class PropertyProcessor extends Processor<PropertyElement, JsonNode> {

	public PropertyProcessor(Context context, PropertyElement element, Expression parentExpression) {
		super(context, element, parentExpression);
	}

	/**
	 * 实现属性节点处理流程
	 */
	@Override
	protected final void process(Processor<? extends Element, ? extends JsonNode> parentNodeProcessor) {
		DataModel dataModel = parentNodeProcessor.getContext().getDataModel();
		Optional<Object> valueOptional = dataModel.getData(expression);
		ObjectNode parentNode = (ObjectNode) parentNodeProcessor.getNode();
		final String showName = super.element.showName();
		if (!valueOptional.isPresent()) {
			// 假如节点支持null时隐藏
			if (!element.isNullHidden()) {
				parentNode.putNull(showName);
			}
			return;
		}
		valueOptional.ifPresent(value -> {
			// 处理转换器
			Optional<Object> optional = element.getConvertor().map(convertor -> convertor.convert(value));
			final Object newValue = optional.orElse(value);
			Class<?> valueClass = newValue.getClass();
			if (support(valueClass)) {
				handle(parentNode, valueClass, newValue, showName);
			}
		});

	}

	/**
	 * 扩展点： 判断是否支持某一类型的值
	 * 
	 * @param sourceClass
	 * @return
	 */
	protected abstract boolean support(Class<?> sourceClass);

	/**
	 * 扩展点：在Json树状结构上构造Node
	 * 
	 * @param parentNode
	 * @param clazz
	 * @param value
	 * @param showName
	 */
	protected abstract void handle(ObjectNode parentNode, Class<?> clazz, Object value, String showName);

}
