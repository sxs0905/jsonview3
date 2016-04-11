package org.developframework.jsonview.core.xml;

import java.io.IOException;
import java.io.InputStream;

/**
 * 配置源接口
 * 
 * @author qiuzhenhao
 *
 */
public interface ConfigurationSource {

	/**
	 * 获得输入流
	 * 
	 * @return
	 * @throws IOException
	 */
	public InputStream getInputStream() throws IOException;

	/**
	 * 获得源的名称
	 * 
	 * @return
	 */
	public String getSourceName();
}
