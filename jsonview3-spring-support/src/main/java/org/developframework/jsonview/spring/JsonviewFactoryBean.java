package org.developframework.jsonview.spring;

import java.util.Set;

import org.developframework.jsonview.core.JsonviewFactory;
import org.springframework.beans.factory.FactoryBean;

public class JsonviewFactoryBean implements FactoryBean<JsonviewFactory> {

	private Set<String> configs;

	@Override
	public JsonviewFactory getObject() throws Exception {
		return new JsonviewFactory(configs);
	}

	@Override
	public Class<?> getObjectType() {
		return JsonviewFactory.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public Set<String> getConfigs() {
		return configs;
	}

	public void setConfigs(Set<String> configs) {
		this.configs = configs;
	}

}
