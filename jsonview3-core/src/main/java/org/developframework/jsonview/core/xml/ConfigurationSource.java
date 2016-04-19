package org.developframework.jsonview.core.xml;

import java.io.IOException;
import java.io.InputStream;

/**
 * Configuration source interface
 * 
 * @author qiuzhenhao
 *
 */
public interface ConfigurationSource {

	/**
	 * get source inputsteam
	 * 
	 * @return inputsteam instance
	 * @throws IOException IOException
	 */
	public InputStream getInputStream() throws IOException;

	/**
	 * get source name
	 * 
	 * @return source name string
	 */
	public String getSourceName();
}
