package test;

import org.developframework.jsonview.core.JsonCreator;
import org.developframework.jsonview.core.JsonviewFactory;
import org.developframework.jsonview.data.DataModel;
import org.developframework.jsonview.data.HashDataModel;

public class Main {

	public static void main(String[] args) {
		User user1 = new User(1, "zhangsan");
		User user2 = new User(2, "lisi");
		User[] users = new User[]{
				user1, user2
		};
		Integer[][] arr = new Integer[][]{
				{
						1, 2, 3
				}, {
						4, 5, 6
				}
		};

		String[] str = new String[]{
				"aaa", "bbb", "ccc"
		};

		JsonviewFactory factory = new JsonviewFactory("/jsonview/jsonview-demo.xml");
		JsonCreator jsonCreator = factory.getJsonCreator();
		DataModel dataModel = new HashDataModel();
		dataModel.putData("user", user1);
		dataModel.putData("users", users);
		dataModel.putData("str", str);
		// System.out.println(dataModel.getData("users[1].likes[1].name"));
		String json = jsonCreator.createJson(dataModel, "namespace", "test-str");
		System.out.println(json);
	}

}
