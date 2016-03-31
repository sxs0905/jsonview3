package org.developframework.jsonview.core.xml;

import java.io.IOException;
import java.io.InputStream;

public interface ConfigurationSource {

	public InputStream getInputStream() throws IOException;

	public String getSourceName();
}
