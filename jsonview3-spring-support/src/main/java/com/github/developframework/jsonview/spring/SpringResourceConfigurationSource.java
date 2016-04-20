package com.github.developframework.jsonview.spring;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.Resource;

import com.github.developframework.jsonview.core.xml.ConfigurationSource;

/**
 * From org.springframework.core.io.Resource configuration source
 * 
 * @author qiuzhenhao
 *
 */
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
