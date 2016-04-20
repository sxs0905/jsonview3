package com.github.developframework.jsonview.springmvc;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.github.developframework.jsonview.core.JsonCreator;
import com.github.developframework.jsonview.core.JsonviewFactory;
import com.github.developframework.jsonview.springmvc.res.JsonviewResponse;

/**
 * jsonview framework HttpMessageConverter
 * 
 * @author qiuzhenhao
 *
 */
public class JsonviewHttpMessageConverter extends MappingJackson2HttpMessageConverter {

	protected JsonviewFactory jsonviewFactory;

	public JsonviewHttpMessageConverter() {

	}

	public JsonviewHttpMessageConverter(JsonviewFactory jsonviewFactory) {
		this.jsonviewFactory = jsonviewFactory;
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return JsonviewResponse.class.isAssignableFrom(clazz);
	}

	@Override
	public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
		return this.canWrite(clazz, mediaType);
	}

	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		JsonviewResponse jsonviewResponse = (JsonviewResponse) object;
		JsonCreator creator = jsonviewFactory.getJsonCreator(super.objectMapper);
		JsonEncoding encoding = super.getJsonEncoding(outputMessage.getHeaders().getContentType());
		JsonGenerator generator = super.objectMapper.getFactory().createGenerator(outputMessage.getBody(), encoding);
		creator.printJson(generator, jsonviewResponse.getDataModel(), jsonviewResponse.getNamespace(), jsonviewResponse.getJsonviewId());
	}

	@Override
	protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		this.writeInternal(object, outputMessage);
	}

	public void setJsonviewFactory(JsonviewFactory jsonviewFactory) {
		this.jsonviewFactory = jsonviewFactory;
	}

	public JsonviewFactory getJsonviewFactory() {
		return jsonviewFactory;
	}
}
