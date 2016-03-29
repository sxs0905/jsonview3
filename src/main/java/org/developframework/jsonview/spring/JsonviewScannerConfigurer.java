package org.developframework.jsonview.spring;

import org.springframework.core.io.Resource;

public class JsonviewScannerConfigurer {

	private Resource[] locations;

	public Resource[] getLocations() {
		return locations;
	}

	public void setLocations(Resource[] locations) {
		this.locations = locations;
	}

}
