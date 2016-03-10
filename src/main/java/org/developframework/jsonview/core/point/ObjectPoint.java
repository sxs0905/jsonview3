package org.developframework.jsonview.core.point;

import org.developframework.jsonview.core.element.ObjectElement;

public abstract class ObjectPoint extends ContainerPoint {

	public abstract void makeObjectJsonNode(ContainerPoint parentPoint, ObjectElement element);
}
