package test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		SimpleDateFormat format = (SimpleDateFormat) context.getBean("defaultDateFormat");
		System.out.println(format.format(new Date()));
		// JsonviewFactory factory = context.getBean("jsonviewFactory",
		// JsonviewFactory.class);
		// JsonCreator jsonCreator = factory.getJsonCreator();
		// DataModel dataModel = new HashDataModel();
		// dataModel.putData("user", user1);
		// dataModel.putData("users", users);
		// dataModel.putData("strName", "xxxxxxxx");
		// String json = jsonCreator.createJson(dataModel, "namespace",
		// "test-user");
		// System.out.println(json);

	}

}
