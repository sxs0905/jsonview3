package test;

import java.util.ArrayList;
import java.util.List;

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
	private List<Favorite> favorites = new ArrayList<>(2);

	@Before
	public void init() {
		User user1 = new User(1, "zhangsan");
		User user2 = new User(2, "lisi");
		users = new User[]{
				user1, user2
		};
		favorites.add(new Favorite(1, "football"));
		favorites.add(new Favorite(2, "basketball"));
		favorites.add(new Favorite(2, "xxxball"));
	}

	@Test
	public void test() {
		JsonCreator jsonCreator = jsonviewFactory.getJsonCreator();
		DataModel dataModel = new HashDataModel();
		dataModel.putData("user", users[0]);
		dataModel.putData("users", users);
		dataModel.putData("favorites", favorites);
		String json = jsonCreator.createJson(dataModel, "jsonview-demo", "user-list");
		System.out.println(json);
		// dataModel.getData("favorites", "userId",
		// 1).ifPresent(System.out::println);

	}

}
