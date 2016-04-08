package org.developframework.jsonview.core.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class UtilDatePropertyHandler implements PropertyHandler<String> {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public String handle(Object source) {
		if (Objects.isNull(source)) {
			return null;
		}
		return sdf.format((Date) source);
	}

}
