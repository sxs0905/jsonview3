package org.developframework.jsonview.core.processor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.developframework.jsonview.core.element.ObjectElement;
import org.developframework.jsonview.data.Expression;
import org.developframework.jsonview.exception.LinkObjectSizeNotEqualException;

/**
 * 一对一链接型处理器
 * 
 * @author qiuzhenhao
 *
 */
public class LinkObjectProcessor extends ObjectProcessor {

	// 数组索引号
	private int index;

	public LinkObjectProcessor(Context context, ObjectElement element, Expression parentExpression) {
		super(context, element, parentExpression);
	}

	/**
	 * 检查数组元素个数是否相同
	 * 
	 * @param parentArraySize
	 */
	public void checkSize(int parentArraySize) {
		Optional<Object> objOptional = context.getDataModel().getData(expression);
		if (objOptional.isPresent()) {
			int size = 0;
			Object obj = objOptional.get();
			if (obj.getClass().isArray()) {
				size = ((Object[]) obj).length;
			} else if (obj instanceof Collection<?>) {
				size = ((List<?>) obj).size();
			}
			if (size != parentArraySize) {
				throw new LinkObjectSizeNotEqualException(element.getData());
			}
		}
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
		this.expression = Expression.buildArrayExpression(expression, index);
	}

}
