package org.developframework.jsonview.springmvc;

import java.io.IOException;
import java.lang.reflect.Type;

import org.developframework.jsonview.core.JsonCreator;
import org.developframework.jsonview.core.JsonviewFactory;
import org.developframework.jsonview.springmvc.res.JsonviewResponse;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;

/**
 * jsonview框架的HttpMessageConverter
 * 
 * @author qiuzhenhao
 *
 */
public class JsonviewHttpMessageConverter extends MappingJackson2HttpMessageConverter {

	protected JsonviewFactory jsonviewFactory;

	public JsonviewHttpMessageConverter(JsonviewFactory jsonviewFactory) {
		this.jsonviewFactory = jsonviewFactory;
	}

	@Override
	public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
		return JsonviewResponse.class.isAssignableFrom(clazz);
	}

	@Override
	protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		JsonviewResponse jsonviewResponse = (JsonviewResponse) object;
		JsonCreator creator = jsonviewFactory.getJsonCreator(super.objectMapper);
		JsonEncoding encoding = super.getJsonEncoding(outputMessage.getHeaders().getContentType());
		JsonGenerator generator = super.objectMapper.getFactory().createGenerator(outputMessage.getBody(), encoding);
		creator.printJson(generator, jsonviewResponse.getDataModel(), jsonviewResponse.getNamespace(), jsonviewResponse.getJsonviewId());
	}

	public JsonviewFactory getJsonviewFactory() {
		return jsonviewFactory;
	}
}
