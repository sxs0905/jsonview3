package org.developframework.jsonview.core.element;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.MappingObjectProcessor;
import org.developframework.jsonview.core.processor.Processor;
import org.developframework.jsonview.data.Expression;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 一对多映射型功能节点
 * 
 * @author qiuhenhao
 *
 */
public class MappingObjectElement extends ObjectElement {

	/**
	 * 映射类型枚举
	 * 
	 * @author qiuzhenhao
	 *
	 */
	public enum MappingType {
		AUTO, // 自动
		SINPLE, // 单个
		MULTIPLE;// 多个

		public static MappingType parse(String typeStr) {
			if (StringUtils.isBlank(typeStr)) {
				return AUTO;
			} else {
				return MappingType.valueOf(typeStr.toUpperCase());
			}
		}
	}

	// 条件源
	private String source;
	// 目标
	private String target;
	// 子对象节点
	private ObjectElement childObjectElement;
	// 子数组节点
	private ArrayElement childArrayElement;
	// 映射类型
	private MappingType mappingType;

	public MappingObjectElement(String data, String alias) {
		super(data, alias);
		childObjectElement = new ObjectElement(data, alias);
		childArrayElement = new ArrayElement(data, alias);
	}

	/**
	 * 重写： 增加节点
	 */
	@Override
	public void addChildElement(Element element) {
		super.addChildElement(element);
		this.childObjectElement.addChildElement(element);
		this.childArrayElement.addChildElement(element);
	}

	/**
	 * 实现： 创建处理器
	 */
	@Override
	public Optional<Processor<? extends Element, ? extends JsonNode>> createProcessor(Context context, ObjectNode parentNode, Expression parentExpression) {
		MappingObjectProcessor processor = new MappingObjectProcessor(context, this, parentExpression);
		Optional<Object> optional = context.getDataModel().getData(processor.getExpression());
		if (optional.isPresent()) {
			return Optional.of(processor);
		}
		if (!nullHidden) {
			parentNode.putNull(this.showName());
		}
		return Optional.empty();
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public MappingType getMappingType() {
		return mappingType;
	}

	public void setMappingType(MappingType mappingType) {
		this.mappingType = mappingType;
	}

	public ObjectElement getChildObjectElement() {
		return childObjectElement;
	}

	public ArrayElement getChildArrayElement() {
		return childArrayElement;
	}

}
