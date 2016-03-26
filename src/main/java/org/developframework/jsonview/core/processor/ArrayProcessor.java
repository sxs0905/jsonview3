package org.developframework.jsonview.core.processor;

import org.developframework.jsonview.core.element.ArrayElement;
import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.data.DataModel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class ArrayProcessor extends ContainerProcessor<ArrayElement, ArrayNode> {

	public ArrayProcessor(Context context, ArrayElement element, ArrayNode node) {
		super(context, element, node);
	}

	@Override
	protected void process(Processor<? extends Element, ? extends JsonNode> parentProcessor) {
		DataModel dataModel = parentProcessor.getContext().getDataModel();
		// Object obj = null;
		// try {
		// obj = dataModel.getData(this.element.getExpression());
		// } catch (NoSuchMethodException e) {
		// e.printStackTrace();
		// return;
		// }
		// if (Objects.isNull(obj))
		// return;
		// int size = 0;
		// if (obj.getClass().isArray()) {
		// size = ((Object[]) obj).length;
		// } else if (obj instanceof Collection<?>) {
		// size = ((Collection<?>) obj).size();
		// }
		// for (int i = 0; i < size; i++) {
		// }
	}

}
