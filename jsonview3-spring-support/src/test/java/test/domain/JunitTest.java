package test.domain;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.developframework.jsonview.core.JsonCreator;
import org.developframework.jsonview.core.JsonviewFactory;
import org.developframework.jsonview.data.DataModel;
import org.developframework.jsonview.data.HashDataModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JunitTest {

	@Resource
	private JsonviewFactory jsonviewFactory;

	@Test
	public void test() {
		DataModel dataModel = new HashDataModel();
		dataModel.putData("number1", 1);
		dataModel.putData("number2", 0);
		dataModel.putData("number3", null);
		dataModel.putData("datetime", LocalDateTime.of(2016, 1, 1, 0, 0, 0));
		JsonCreator jsonCreator = jsonviewFactory.getJsonCreator();
		String json = jsonCreator.createJson(dataModel, "jsonview-student", "virtual-student-view", true);
		System.out.println(json);
	}
}
