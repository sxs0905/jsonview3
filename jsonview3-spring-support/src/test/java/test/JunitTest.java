package test;

import javax.annotation.Resource;

import org.developframework.jsonview.core.JsonCreator;
import org.developframework.jsonview.core.JsonviewFactory;
import org.developframework.jsonview.data.DataModel;
import org.developframework.jsonview.data.HashDataModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JunitTest {

	@Resource
	private JsonviewFactory jsonviewFactory;

	private User[] users;

	@Before
	public void init() {
		User user1 = new User(1, "zhangsan");
		User user2 = new User(2, "lisi");
		users = new User[]{
				user1, user2
		};
	}

	@Test
	public void test() {
		JsonCreator jsonCreator = jsonviewFactory.getJsonCreator();
		DataModel dataModel = new HashDataModel();
		dataModel.putData("user", users[0]);
		dataModel.putData("users", users);
		String json = jsonCreator.createJson(dataModel, "jsonview-demo", "user-detail");
		System.out.println(json);

	}

}
