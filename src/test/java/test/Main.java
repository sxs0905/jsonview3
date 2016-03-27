package test;

import org.developframework.jsonview.core.element.Jsonview;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.element.JsonviewPackage;
import org.developframework.jsonview.core.processor.Context;
import org.developframework.jsonview.core.processor.JsonviewProcessor;
import org.developframework.jsonview.core.xml.JsonviewConfigurationSaxReader;

import com.fasterxml.jackson.databind.ObjectMapper;

import ognl.OgnlContext;

public class Main {

	public static void main(String[] args) {
		JsonviewConfigurationSaxReader reader = new JsonviewConfigurationSaxReader(new String[]{"/jsonview/jsonview-demo.xml"});
		JsonviewConfiguration configuration = reader.readConfiguration();
		JsonviewPackage jsonviewPackage = configuration.getJsonviewPackageByNamespace("xxx");
		Jsonview jsonview = jsonviewPackage.getJsonviewById("id");
		Context context = new Context();
		ObjectMapper objectMapper = new ObjectMapper();
		context.setJsonviewConfiguration(configuration);
		context.setOgnlContext(new OgnlContext());
		context.setObjectMapper(objectMapper);
		JsonviewProcessor processor = new JsonviewProcessor(context, jsonview, objectMapper.createObjectNode());
		processor.process(null);
	}

}
