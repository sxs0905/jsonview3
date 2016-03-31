package org.developframework.jsonview.core.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import org.developframework.jsonview.exception.ConfigurationFileNotFoundException;

public class FileConfigurationSource implements ConfigurationSource {

	private String filename;

	public FileConfigurationSource(String filename) {
		this.filename = filename;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		InputStream is = this.getClass().getResourceAsStream(filename);
		if (Objects.isNull(is)) {
			throw new ConfigurationFileNotFoundException(filename);
		}
		return is;
	}

	@Override
	public String getSourceName() {
		return filename;
	}

}