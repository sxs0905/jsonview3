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
	 * @return 输入流
	 * @throws IOException IO异常
	 */
	public InputStream getInputStream() throws IOException;

	/**
	 * 获得源的名称
	 * 
	 * @return 源的名称
	 */
	public String getSourceName();
}
