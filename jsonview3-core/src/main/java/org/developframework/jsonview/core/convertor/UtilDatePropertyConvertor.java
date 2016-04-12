package org.developframework.jsonview.core.convertor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class UtilDatePropertyConvertor implements PropertyConvertor<String> {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public String convert(Object source) {
		if (Objects.isNull(source)) {
			return null;
		}
		return sdf.format((Date) source);
	}
}
