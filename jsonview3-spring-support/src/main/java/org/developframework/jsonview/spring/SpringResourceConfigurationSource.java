package org.developframework.jsonview.spring;

import java.io.IOException;
import java.io.InputStream;

import org.developframework.jsonview.core.xml.ConfigurationSource;
import org.springframework.core.io.Resource;

public class SpringResourceConfigurationSource implements ConfigurationSource {

	private Resource resource;

	public SpringResourceConfigurationSource(Resource resource) {
		this.resource = resource;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return resource.getInputStream();
	}

	@Override
	public String getSourceName() {
		return resource.getFilename();
	}

}