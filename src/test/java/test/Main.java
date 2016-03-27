package test;

import org.developframework.jsonview.data.DataModel;
import org.developframework.jsonview.data.HashDataModel;
import org.developframework.jsonview.utils.ExpressionUtils;

public class Main {

	public static void main(String[] args) {
		User user1 = new User(1, "zhangsan");
		User user2 = new User(2, "lisi");
		User[] users = new User[]{
				user1, user2
		};
		// JsonviewFactory factory = new
		// JsonviewFactory("/jsonview/jsonview-demo.xml");
		// JsonCreator jsonCreator = factory.getJsonCreator();
		DataModel dataModel = new HashDataModel();
		dataModel.putData("user", user1);
		dataModel.putData("users", users);
		System.out.println(dataModel.getData("users[1].name"));
		System.out.println(ExpressionUtils.getValue(users, "users[1].name"));
		// String json = jsonCreator.createJson(dataModel, "namespace", "id");
		// System.out.println(json);
	}

}
