package com.github.developframework.jsonview.spring;

import java.util.Set;

import org.springframework.beans.factory.FactoryBean;

import com.github.developframework.jsonview.core.JsonviewFactory;

/**
 * JsonviewFactory's spring FactoryBean
 * 
 * @author qiuzhenhao
 *
 */
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
