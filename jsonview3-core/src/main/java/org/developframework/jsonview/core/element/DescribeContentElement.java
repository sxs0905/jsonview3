package org.developframework.jsonview.core.element;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

/**
 * The element for describe json tree content
 * 
 * @author qiuzhenhao
 *
 */
public abstract class DescribeContentElement extends Element {

	protected String data;
	protected String alias;
	protected boolean nullHidden;

	public DescribeContentElement(String data, String alias) {
		this.data = data;
		this.alias = alias;
	}

	/**
	 * show name
	 * 
	 * @return name to display in json string
	 */
	public String showName() {
		if (StringUtils.isNotBlank(alias)) {
			return alias;
		}
		String data = ignoreHeadSign(this.data);
		final int dotLastIndex = data.lastIndexOf(".");
		data = dotLastIndex == -1 ? data : data.substring(dotLastIndex + 1);
		return camelCaseToUnderline(data);
	}

	/**
	 * ignore head sign "#"
	 * 
	 * @param data
	 * @return
	 */
	private final String ignoreHeadSign(String data) {
		Objects.requireNonNull(data);
		if (data.startsWith("#")) {
			return data.substring(1);
		}
		return data;
	}

	/**
	 * camel case string to underline string
	 * 
	 * @param camelCaseStr camel case string
	 * @return underline string
	 */
	private final String camelCaseToUnderline(String camelCaseStr) {
		final StringBuffer sb = new StringBuffer();
		for (int i = 0, size = camelCaseStr.length(); i < size; i++) {
			char ch = camelCaseStr.charAt(i);
			if (ch >= 'A' && ch <= 'Z') {
				if (i == 0) {
					sb.append((char) (ch + 32));
				} else {
					sb.append('_').append((char) (ch + 32));
				}
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	/**
	 * get data
	 * 
	 * @return data
	 */
	public String getData() {
		return data;
	}

	/**
	 * set data
	 * 
	 * @param data data string
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * get alias
	 * 
	 * @return alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * check nullHidden
	 * 
	 * @return boolean
	 */
	public boolean isNullHidden() {
		return nullHidden;
	}

	/**
	 * set nullHidden
	 * 
	 * @param nullHiddenStr nullHidden value
	 */
	public void setNullHidden(String nullHiddenStr) {
		this.nullHidden = StringUtils.isBlank(nullHiddenStr) ? false : new Boolean(nullHiddenStr).booleanValue();
	}

	/**
	 * rewrite: equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Element) {
			return data.equals(((DescribeContentElement) obj).getData()) || this == obj;
		}
		return false;
	}
}
