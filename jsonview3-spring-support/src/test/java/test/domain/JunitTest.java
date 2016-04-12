package test.domain;

import org.developframework.jsonview.core.JsonCreator;
import org.developframework.jsonview.core.JsonviewFactory;
import org.developframework.jsonview.data.DataModel;
import org.developframework.jsonview.data.HashDataModel;
import org.junit.Before;
import org.junit.Test;

public class JunitTest {

	private JsonviewFactory jsonviewFactory;

	@Before
	public void init() {
		jsonviewFactory = new JsonviewFactory("/jsonview/jsonview-student.xml");
	}

	@Test
	public void test() {
		DataModel dataModel = new HashDataModel();
		Student student1 = new Student(1, "Peter", null);
		Student student2 = new Student(2, "Tom", "2016-02-02");
		Student[] students = new Student[]{
				student1, student2
		};
		dataModel.putData("students", students);

		JsonCreator jsonCreator = jsonviewFactory.getJsonCreator();
		String json = jsonCreator.createJson(dataModel, "jsonview-student", "student-list", true);
		System.out.println(json);
	}
}
