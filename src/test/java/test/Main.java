package test;

import java.util.Iterator;

import org.developframework.jsonview.core.element.Element;
import org.developframework.jsonview.core.element.Jsonview;
import org.developframework.jsonview.core.element.JsonviewConfiguration;
import org.developframework.jsonview.core.element.JsonviewPackage;
import org.developframework.jsonview.core.xml.JsonviewConfigurationSaxReader;

public class Main {

	public static void main(String[] args) {
		JsonviewConfigurationSaxReader reader = new JsonviewConfigurationSaxReader(new String[]{"/jsonview/jsonview-demo.xml"});
		JsonviewConfiguration configuration = reader.readConfiguration();
		JsonviewPackage jsonviewPackage = configuration.getJsonviewPackageByNamespace("xxx");
		Jsonview jsonview = jsonviewPackage.getJsonviewById("id");

		for (Iterator<Element> iterator = jsonview.elementIterator(); iterator.hasNext();) {
			Element element = iterator.next();
			System.out.println(element.showName());
		}

	}

}
