**Jsonview3框架说明文档**
===
[TOC]
## 1. 概述
Jsonview框架构建于jackson框架之上，实现通过XML文件配置来自定义json格式，大大提升了java生成json字符串的自由性，让开发模块化更加便捷快速。
### **1.1. 运行环境**
JDK8及以上
### **1.2. 使用方式**
maven
```
<dependency>
	<groupId>org.developframework</groupId>
	<artifactId>jsonview3-core</artifactId>
	<version>${jsonview.version}</version>
</dependency>
```
### **1.3. 依赖项**

+ commons-lang3.jar
+ slf4j-api.jar
+ jackson-core.jar
+ jackson-databind.jar
+ jackson-annotations.jar

## **2. HelloWorld**
一个最简单的jsonview使用示例：
```
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
		jsonviewFactory = new JsonviewFactory("/jsonview/jsonview-demo.xml");
	}

	@Test
	public void test() {
		JsonCreator jsonCreator = jsonviewFactory.getJsonCreator();
		DataModel dataModel = new HashDataModel();
		dataModel.putData("sayHello", "Hello Jsonview!");
		String json = jsonCreator.createJson(dataModel, "jsonview-demo", "first-view");
		System.out.println(json);
	}
}
```
你需要一份jsonview XML配置，位置在上述声明的/jsonview/jsonview-demo.xml：
```
<jsonview-configuration xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://www.developframework.org/schema/jsonview3" 
	xsi:schemaLocation="
	http://www.developframework.org/schema/jsonview3 
	http://www.developframework.org/schema/jsonview3/jsonview-configuration-3.0.xsd">
	<jsonview-package namespace="jsonview-demo">
		<jsonview id="first-view">
			<property data="sayHello"/>
		</jsonview>
	</jsonview-package>
</jsonview-configuration>
```
运行结果：
```
{"say_hello":"Hello Jsonview!"}
```

## **3. 概览**
### **3.1. java概览**
#### **3.1.1. DataModel**
`org.developframework.jsonview.data.DataModel`接口是jsonview框架的数据模型。用于装载需要在json视图中渲染的数据，数据由键值对构成。接口提供存入和取出数据的方法。
目前实现类仅有`org.developframework.jsonview.data.HashDataModel`
存取数据范例：
```
DataModel dataModel = new HashDataModel();
dataModel.putData("sayHello", "Hello Jsonview!");
Optional<Object> value = dataModel.getData("sayHello");
value.ifPresent(System.out::println);
```
#### **3.1.2. Expression**

`org.developframework.jsonview.data.Expression`类是jsonview框架从DataModel中提取数据的表达式。不论dataModel存的是java实体类还是Map对象都可以使用表达式取值。
范例：

+ `student` 你可以从DataModel对象内取得名为student的对象
+ `#student` 你可以从DataModel对象内 **强制从根路径** 取得名为student的对象
+ `student.name` 你可以从DataModel对象内取得名为student的对象的name属性值
+ `students[0]` 你可以从DataModel对象内取得名为students的数组内的第1个元素
+ `student[0].name` 你可以从DataModel对象内取得名为students的数组内的第1个元素的name属性值

#### **3.1.3. JsonviewFactory**
`org.developframework.jsonview.core.JsonviewFactory`类是jsonview框架的构建工厂。使用jsonview框架的第一步就是建立该对象。
建立该对象需要提供配置文件路径的字符串，多份配置文件可以采用字符串数组。
```
final String[] configs = {"config1.xml", "config2.xml"};
JsonviewFactory jsonviewFactory = new JsonviewFactory(configs);
```
#### **3.1.4. JsonviewConfiguration**
`org.developframework.jsonview.core.element.JsonviewConfiguration`类为Jsonview框架的总配置文件，可以从JsonviewFactory中得到该对象。
```
JsonviewConfiguration jsonviewConfiguration = jsonviewFactory.getJsonviewConfiguration();
```
#### **3.1.5. JsonCreator**
`org.developframework.jsonview.core.JsonCreator`接口是json字符串建造类，执行一次生成json字符串的操作需要构建该对象。JsonCreator由JsonviewFactory生成。
该对象提供三个构建json字符串的方法：
```
public String createJson(DataModel dataModel, String namespace, String id, boolean isPretty);
```
返回json字符串

+ isPretty=true时可以美化json
```
public String createJson(DataModel dataModel, String namespace, String id);
```
返回json字符串，不美化
```
public void printJson(JsonGenerator generator, DataModel dataModel, String namespace, String id);
```
将json输出到ObjectMapper的generator对象

#### **3.1.6. Jsonview**
`org.developframework.jsonview.core.element.Jsonview`类，一个Jsonview类的实例代表一种json的视图模板。它由`namespace`和`id`唯一确定。可以通过以下方法得到Jsonview实例：
```
Jsonview jsonview = jsonviewConfiguration.extractJsonview("namespace", "id");
```
#### **3.1.7. JsonviewPackage**
`org.developframework.jsonview.core.element.JsonviewPackage`类，一个JsonviewPackage实例是一个命名空间，可以装载若干个Jsonview实例。推荐将Jsonview按功能有序存放于JsonviewPackage。通过以下方法得到JsonviewPackage对象：
```
JsonviewPackage jsonviewPackage = jsonviewConfiguration.getJsonviewPackageByNamespace("namespace");
```

### **3.2. XML概览**
#### **3.2.1. 结构**
Jsonview configuration 文档的结构如下：
```
<jsonview-configuration>
    <jsonview-package namespace="">
    	<jsonview id="">
    		<!-- 定义视图内容 -->
    	</jsonview>
    	<jsonview id="">
    		<!-- 定义视图内容 -->
    	</jsonview>
    	<!-- 其它jsonview -->
    </jsonview-package>
    <!-- 其它jsonview-package -->
</jsonview-configuration>
```
在`<jsonview-configuration>`节点中你可以配置任意数量的`<jsonview-package>`，代表不同的视图包，在`<jsonview-package>`节点上你必须声明命名空间`namespace`属性，并且`namespace`是唯一的，不然会抛出`ResourceNotUniqueException`。

在每个`<jsonview-package>`节点中你可以配置任意数量的`<jsonview>`。每个`<jsonview>`即代表了某一种json格式的视图，在`<jsonview>`节点你必须声明id属性，并且id必须是唯一的，不然会抛出`ResourceNotUniqueException`。

Jsonview configuration文档不是唯一的，Jsonview框架允许你拥有多份的Jsonview configuration配置文档，文档的加载顺序不分先后。
#### **3.2.2. 标签**
基本型标签

+ `<jsonview>`
+ `<object>`
+ `<array>`
+ `<property>`

功能型标签

+ `<import>`
+ `<link-object>`
+ `<mapping-object>`
+ `<virtual-object>`
+ `<ignore-property>`

拓展型标签

+ `<property-date>`
+ `<property-unixtimestamp>`
+ `<property-boolean>`

##### **3.2.2.1. 基本型标签**
###### a) jsonview
当你需要声明一个json格式模板时，你将会使用到`<jsonview>`标签。
```
<jsonview id="" data="" for-class=""></jsonview>
```

|属性|功能|是否必须|
|---|---|---|
|id|声明模板编号，在命名空间中唯一|是|
|data|取值表达式|否|
|for-class|声明data表达式指向的对象类型|否|

###### b) object
当你需要在json中构建一个对象结构时，你将会使用到`<object>`标签。
```
<object data="" alias="" for-class="" null-hidden="true"></object>
```
|属性|功能|是否必须|
|---|---|---|
|data|取值表达式|是|
|alias|别名，你可以重新定义显示名|否|
|for-class|声明data表达式指向的对象类型|否|
|null-hidden|true时表示表达式取的值为null时隐藏该节点，默认为false|否|
###### c) array
当你需要在json中构建一个数组结构时，你将会使用到`<array>`标签。
```
<array data="" alias="" for-class="" null-hidden="true"></array>
```
|属性|功能|是否必须|
|---|---|---|
|data|取值表达式|是
|alias|别名，你可以重新定义显示名|否|
|for-class|声明data表达式指向的对象类型|否|
|null-hidden|true时表示表达式取的值为null时隐藏该节点，默认为false|否|
`<array>`标签可以没有子标签，这时表示数组为基本类型数组。
###### d) property
当你需要在json中构建一个普通属性结构时， 你将会使用到`<property>`标签。
```
<property data="" alias="" convertor="" null-hidden="true"/>
```
|属性|功能|是否必须|
|---|---|---|
|data|取值表达式|是|
|alias|别名，你可以重新定义显示名|否|
|convertor|类型转换器全限定类名|否|
|null-hidden|true时表示表达式取的值为null时隐藏该节点，默认为false|否|

##### **3.2.2.2. 功能型标签**
###### a) import
Jsonview框架提供模块化设计json结构视图的功能。在一个`<jsonview>`标签中你可以采用`<import>`标签来导入其它的`<jsonview>`的结构内容，从而实现模块化单元分解。详见[5.3.节](#chapter53)
```
<import id="" namespace=""/>
```

|属性|功能|是否必须|
|---|---|---|
|id|需要导入的jsonview id|是|
|namespace|jsonview的命名空间，不填默认为当前命名空间|否|

###### b) link-object

该标签用于实现一对一链接功能。详见[5.4.1.节](#chapter541)。

```
<link-object data="" alias="" for-class="" null-hidden="true"></link-object>
```

|属性|功能|是否必须|
|---|---|---|
|data|取值表达式|是|
|alias|别名，你可以重新定义显示名|否|
|for-class|声明data表达式指向的对象类型|否|
|null-hidden|true时表示表达式取的值为null时隐藏该节点，默认为false|否|

###### c) mapping-object

该标签用于实现一对多映射功能。详见[5.4.2.节](#chapter542)
```
<mapping-object data="" source=""  target="" type="auto" alias="" for-class="" null-hidden="true"></mapping-object>
```
|属性|功能|是否必须|
|---|---|---|
|data|取值表达式|是|
|source|比较源|是|
|target|比较目标|是|
|type|映射类型，可选值auto、sinple、multiple，不填默认为auto|否|
|alias|别名，你可以重新定义显示名|否|
|for-class|声明data表达式指向的对象类型|否|
|null-hidden|true时表示表达式取的值为null时隐藏该节点，默认为false|否|
###### d) virtual-object
该标签用于虚拟对象结构。详见[5.2.节](#chapter52)
```
<virtual-object alias=""></virtual-object>
```
|属性|功能|是否必须|
|---|---|---|
|alias|别名|是|
###### e) ignore-property
忽略属性，需结合`for-class`属性使用。详见[4.5.节](#chapter45)
```
<ignore-property name=""/>
```
|属性|功能|是否必须|
|---|---|---|
|name|类中忽略的属性名称|是|
##### **3.2.2.3. 拓展型标签**
###### a) property-date
该标签拓展于`<property>`，针对时间日期类型，使用`pattern`属性来格式化日期时间。详见[4.3.1.节](#chapter431)

|拓展属性|功能|是否必须|
|---|---|---|
|pattern|格式化模板字符串，不填默认为"yyyy-MM-dd HH:mm:ss"|否|

支持的时间日期类型：

+ java.util.Date
+ java.util.Calendar
+ java.sql.Date
+ java.sql.Time
+ java.sql.Timestamp
+ (JDK8) java.time.LocalDate
+ (JDK8) java.time.LocalDateTime
+ (JDK8) java.time.LocalTime
+ (JDK8) java.time.Instant

###### b) property-unixtimestamp
该标签拓展于`<property>`，针对时间日期类型，可以将时间日期类型转化为unix时间戳格式显示。可支持的时间日期类型同`<property-date>`。详见[4.3.2.节](#chapter432)
###### c) property-boolean
该标签拓展于`<property>`，可以将数字类型（short、int、long）变为boolean型，非0值为true，0值为false。详见[4.3.3节](#chapter433)
## **4. 基本使用**
模型声明（以下各小节示例代码均使用这些模型实体类）：
学生类 test.domain.Student.java
```
package test.domain;
import java.text.ParseException;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

public class Student {

	private int id;
	private String name;
	private int classId;
	private Date birthday;

	public Student(int id, String name, int classId, String birthday) {
		this.id = id;
		this.name = name;
		this.classId = classId;
	    try {
			this.birthday = StringUtils.isBlank(birthday) ? null : DateUtils.parseDate(birthday, "yyyy-MM-dd");
		} catch (ParseException e) {
			this.birthday = new Date();
		}
	}
	// 忽略 getter & setter
}
```
账户类 test.domain.Account.java
```
public class Account {

	private String username;
	private String password;

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	// 忽略 getter & setter
}
```
班级类 test.domain.SchoolClass.java
```
public class SchoolClass {

	private int id;
	private String className;

	public SchoolClass(int id, String className) {
		this.id = id;
		this.className = className;
	}
	// 忽略 getter & setter
}
```
### **4.1. 简单输出模型对象Json**
/jsonview/jsonview-student.xml
```
<jsonview-configuration xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://www.developframework.org/schema/jsonview3" 
	xsi:schemaLocation="
	http://www.developframework.org/schema/jsonview3 
	http://www.developframework.org/schema/jsonview3/jsonview-configuration-3.0.xsd">
	<jsonview-package namespace="jsonview-student">
		<jsonview id="student-detail-view" data="student">
			<property data="id"/>
			<property data="name"/>
			<property data="classId"/>
			<property data="birthday"/>
		</jsonview>
	</jsonview-package>
</jsonview-configuration>
```
JunitTest.java
```
// 忽略 import
public class JunitTest {

	private JsonviewFactory jsonviewFactory;

	@Before
	public void init() {
		jsonviewFactory = new JsonviewFactory("/jsonview/jsonview-student.xml");
	}

	@Test
	public void test() {
		DataModel dataModel = new HashDataModel();
		Student student = new Student(1, "Peter", 1, "2016-01-01");
		dataModel.putData("student", student);

		JsonCreator jsonCreator = jsonviewFactory.getJsonCreator();
		String json = jsonCreator.createJson(dataModel, "jsonview-student", "student-detail-view", true);
		System.out.println(json);
	}
}
```
运行结果：
```
{
  "id" : 1,
  "name" : "Peter",
  "class_id" : 1,
  "birthday" : "Fri Jan 01 00:00:00 CST 2016"
}
```
### **4.2. 使用alias修改显示名称**
/jsonview/jsonview-student.xml
```
<!-- 忽略 jsonview-package -->
<jsonview id="student-detail-view" data="student">
	<property data="id" alias="student_id"/>
	<property data="name" alias="student_name"/>
	<property data="classId" alias="student_class_id/>
	<property data="birthday" alias="student_birthday"/>
</jsonview>
```
运行结果：
```
{
  "student_id" : 1,
  "student_name" : "Peter",
  "student_class_id" : 1,
  "student_birthday" : "Fri Jan 01 00:00:00 CST 2016"
}
```
### **4.3. property扩展**
#### <a name="chapter431">**4.3.1 使用property-date格式化日期时间**</a>
该标签可以格式化时间。
把4.1.节代码
```
<property data="birthday"/>
```
替换为
```
<property-date data="birthday" pattern="yyyy-MM-dd"/>
```
运行结果：
```
{
  "id" : 1,
  "name" : "Peter",
  "class_id" : 1,
  "birthday" : "2016-01-01"
}
```
#### <a name="chapter432">**4.3.2 使用property-unixtimestamp输出时间戳**</a>
该标签可以使日期时间类型转化成时间戳形式输出。
```
DataModel dataModel = new HashDataModel();
dataModel.putData("datetime", LocalDateTime.of(2016, 1, 1, 0, 0, 0));
```

```
{
  "datetime" : 1451577600
}
```
#### <a name="chapter433">**4.3.3 使用property-boolean转换**</a>
该标签可以把非0数字转换成true，0转换成false
```
DataModel dataModel = new HashDataModel();
dataModel.putData("number1", 1);
dataModel.putData("number2", 0);
```
```
{
  "number1" : true,
  "number2" : false
}
```
### **4.4. 使用null-hidden隐藏值为null的属性**
把4.1.节代码
```
<property data="birthday"/>
```
替换为
```
<property data="birthday" null-hidden="true"/>
```
student实例传入null的birthday值
```
Student student = new Student(1, "Peter", null);
```
运行结果：
```
{
  "id" : 1,
  "name" : "Peter",
  "class_id" : 1
}
```
### <a name="chapter45">**4.5. 使用for-class输出模型对象Json**</a>
/jsonview/jsonview-student.xml 修改
```
<!-- 忽略 jsonview-package -->
<jsonview id="student-detail-view" data="student" for-class="test.domain.Student">
</jsonview>
```
运行结果和4.1.节相同。
使用`ignore-property`忽略不需要的属性：
/jsonview/jsonview-student.xml 添加
```
<!-- 忽略 jsonview-package -->
<jsonview id="student-detail-view" data="student" for-class="test.domain.Student">
	<ignore-property data="birthday"/>
</jsonview>
```
运行结果：
```
{
  "id" : 1,
  "name" : "Peter",
  "class_id" : 1
}
```
### **4.6. 简单输出数组模型Json**
/jsonview/jsonview-student.xml
```
<!-- 忽略 jsonview-package -->
<jsonview id="student-list-view">
	<array data="students">
		<property data="id"/>
		<property data="name"/>
		<property data="classId"/>
		<property-date data="birthday" pattern="yyyy-MM-dd"/>
	</array>
</jsonview>
```
JunitTest.java
```
DataModel dataModel = new HashDataModel();
Student student1 = new Student(1, "Peter", 1, "2016-01-01");
Student student2 = new Student(2, "Tom", 1, "2016-02-02");
Student[] students = new Student[]{student1, student2};
dataModel.putData("students", students);
JsonCreator jsonCreator = jsonviewFactory.getJsonCreator();
String json = jsonCreator.createJson(dataModel, "jsonview-student", "student-list-view", true);
System.out.println(json);

```
运行结果：
```
{
  "students" : [ {
    "id" : 1,
    "name" : "Peter",
    "class_id": 1,
    "birthday" : "2016-01-01"
  }, {
    "id" : 2,
    "name" : "Tom",
    "class_id": 1,
    "birthday" : "2016-02-02"
  } ]
}
```
## **5. 高级功能**
### **5.1. Property的转换器**
`org.developframework.jsonview.core.convertor.PropertyConvertor`
接口可以对表达式选取的属性值进行自定义转换。
```
package org.developframework.jsonview.core.convertor;

public interface PropertyConvertor<TARGET> {

	public TARGET convert(Object source);
}
```

+ 泛型TARGET为转化后的类型。

范例：实现不使用property-date进行日期格式化。

*此为Jsonview框架内置实现类*
```
package org.developframework.jsonview.core.convertor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class UtilDatePropertyConvertor implements PropertyConvertor<String> {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public String convert(Object source) {
		if (Objects.isNull(source)) {
			return null;
		}
		return sdf.format((Date) source);
	}
}
```
```
<property data="birthday" convertor="org.developframework.jsonview.core.convertor.UtilDatePropertyConvertor"/>
```
运行结果：
```
{
  "id" : 1,
  "name" : "Peter",
  "class_id": 1,
  "birthday" : "2016-01-01 00:00:00"
}
```
### <a name="chapter52">**5.2. 虚拟结构**</a>
使用`<virtual-object>`可以虚拟一个对象结构。
范例：仅有id和name属性值，虚拟出一个virtual_student对象来包裹id和name值。
/jsonview/jsonview-student.xml
```
<jsonview id="virtual-student-view">
	<virtual-object alias="virtual_student">
		<property data="id"/>
		<property data="name"/>
	</virtual-object>
</jsonview>
```
JunitTest.java
```
DataModel dataModel = new HashDataModel();
//仅有属性值
dataModel.putData("id", 1);
dataModel.putData("name", "Peter");
JsonCreator jsonCreator = jsonviewFactory.getJsonCreator();
String json = jsonCreator.createJson(dataModel, "jsonview-student", "virtual-student-view", true);
System.out.println(json);
```
运行结果：
```
{
  "virtual_student" : {
    "id" : 1,
    "name" : "Peter"
  }
}
```
### <a name="chapter53">**5.3. 模块化**</a>
使用`<import>`标签引用其它`<jsonview>`模板，从而可实现模块化设计，避免重复定义视图。
修改4.1.节的代码
```
<!-- 详情视图 -->
<jsonview id="student-detail-view" data="student">
    <import id="student-detail"/>
</jsonview>

<!-- 列表视图 -->
<jsonview id="student-list-view">
    <array data="students">
    	<import id="student-detail"/>
    </array>
</jsonview>

<!-- 详情项 -->
<jsonview id="student-detail">
    <property data="id"/>
    <property data="name"/>
    <property data="classId"/>
    <property-date data="birthday" pattern="yyyy-MM-dd"/>
</jsonview>
```
### **5.4. 链接与映射**
#### <a name="chapter541">**5.4.1. 一对一数组链接**</a>
使用`<link-object>`标签可以在数组间一对一链接对象。**该标签仅能在`<array>`下使用。**当`<link-object>`的data属性所指的数组和父`<array>`数组个数不相同时将会抛出`LinkObjectSizeNotEqualException`。
范例：
假如每个学生实例都有一个账户实例。
JunitTest.java
```
Student student1 = new Student(1, "Peter", 1, "2016-01-01");
Student student2 = new Student(2, "Tom", 1, "2016-02-02");
Student[] students = new Student[]{student1, student2};
Account account1 = new Account("PeterAccount", "PeterPassword");
Account account2 = new Account("TomAccount", "TomPassword");
Account[] accounts = new Account[]{account1, account2};
DataModel dataModel = new HashDataModel();
// 学生实例组
dataModel.putData("students", students);
// 账户实例组
dataModel.putData("accounts", accounts);
// ignore other code
```
jsonview-account.xml
```
<!-- 忽略 jsonview-configuration -->
<jsonview-package namespace="jsonview-account">
	<jsonview id="account-detail">
		<property data="username"/>
		<property data="password"/>
	</jsonview>
</jsonview-package>
```
jsonview-student.xml
```
<jsonview id="student-list-view">
	<array data="students">
		<import id="student-detail"/>
		<link-object data="#accounts" alias="account">
			<!-- 引用另一个命名空间的视图 -->
			<import id="account-detail" namespace="jsonview-account"/>
		</link-object>
	</array>
</jsonview>

<jsonview id="student-detail">
	<property data="id"/>
	<property data="name"/>
	<property-date data="birthday" pattern="yyyy-MM-dd"/>
</jsonview>
```
data="#accounts"表示从dataModel根路径取accounts对象。
运行结果：
```
{
    "students" : [ {
        "id" : 1,
        "name" : "Peter",
        "birthday" : "2016-01-01",
        "account" : {
            "username" : "PeterAccount",
            "password" : "PeterPassword"
        }
    }, {
        "id" : 2,
        "name" : "Tom",
        "birthday" : "2016-02-02",
        "account" : {
            "username" : "TomAccount",
            "password" : "TomPassword"
        }
    } ]
}
```
#### <a name="chapter542">**5.4.2. 一对多映射**</a>
使用`<mapping-object>`标签可以在数组间一对多映射对象。`source`属性指定条件比较源，`target`属性指定条件比较目标，`type`属性指定映射类型，可以选值：
`auto`表示自动根据映射元素的个数决定采用对象结构还是数组结构。
`sinple`表示明确为一对一映射，使用对象结构。
`multiple`表示明确为一对多映射，使用数组结构。

范例：一个班级有多名学生，现在有三个班级实例组成的班级组，五名学生实例组成学生组。其中
Peter和Tom是1班学生，
Lucy和Bob是2班学生，
Bill是3班学生。
映射的条件为Student的classId属性等于SchoolClass的id属性。
现在需要把该关系融合到一个json中。
/jsonview/jsonview-class.xml
```
<!-- 忽略 jsonview-configuration -->
<jsonview-package namespace="jsonview-class">
	<!-- 班级详情 -->
	<jsonview id="class-detail">
		<property data="id"/>
		<property data="className"/>
		<mapping-object data="#students" source="id"  target="classId" alias="student" type="multiple">
			<!-- 引用学生详情视图 -->
			<import id="student-detail" namespace="jsonview-student"/>
		</mapping-object>
	</jsonview>
	<!-- 班级列表视图 -->
	<jsonview id="class-list-view">
		<array data="schoolClasses">
			<import id="class-detail"/>
		</array>
	</jsonview>
</jsonview-package>
```
JunitTest.java
```
SchoolClass schoolClass1 = new SchoolClass(1, "一年级1班");
SchoolClass schoolClass2 = new SchoolClass(2, "一年级2班");
SchoolClass schoolClass3 = new SchoolClass(3, "一年级3班");
SchoolClass[] schoolClasses = new SchoolClass[]{
		schoolClass1, schoolClass2, schoolClass3
};
// 1班成员
Student student1 = new Student(1, "Peter", 1, "2016-01-01");
Student student2 = new Student(2, "Tom", 1, "2016-02-02");
// 2班成员
Student student3 = new Student(3, "Lucy", 2, "2016-03-03");
Student student4 = new Student(4, "Bob", 2, "2016-04-04");
// 3班成员
Student student5 = new Student(5, "Bill", 3, "2016-05-05");
Student[] students = new Student[]{
		student1, student2, student3, student4, student5
};
DataModel dataModel = new HashDataModel();
// 学生实例组
dataModel.putData("students", students);
// 班级实例组
dataModel.putData("schoolClasses", schoolClasses);
JsonCreator jsonCreator = jsonviewFactory.getJsonCreator();
String json = jsonCreator.createJson(dataModel, "jsonview-class", "class-list-view", true);
System.out.println(json);
```
运行结果：
```
{
  "school_classes" : [ {
    "id" : 1,
    "class_name" : "一年级1班",
    "student" : [ {
      "id" : 1,
      "name" : "Peter",
      "class_id" : 1,
      "birthday" : "2016-01-01"
    }, {
      "id" : 2,
      "name" : "Tom",
      "class_id" : 1,
      "birthday" : "2016-02-02"
    } ]
  }, {
    "id" : 2,
    "class_name" : "一年级2班",
    "student" : [ {
      "id" : 3,
      "name" : "Lucy",
      "class_id" : 2,
      "birthday" : "2016-03-03"
    }, {
      "id" : 4,
      "name" : "Bob",
      "class_id" : 2,
      "birthday" : "2016-04-04"
    } ]
  }, {
    "id" : 3,
    "class_name" : "一年级3班",
    "student" : [ {
      "id" : 5,
      "name" : "Bill",
      "class_id" : 3,
      "birthday" : "2016-05-05"
    } ]
  } ]
}
```
## **6. 日志**
Jsonview框架使用slf4j-api日志接口，提供内部日志打印功能。可以使用log4j或者logback打印日志。
以下示例使用logback
logback.xml
```
<configuration scan="true" scanPeriod="60 seconds" debug="false">
	<contextName>jsonview-log</contextName>
	<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
		<encoder>
			<pattern>%d{HH:mm:ss.SSS} %-5level - %msg%n
			</pattern>
		</encoder>
	</appender>
	<logger name="org.developframework.jsonview" level="INFO" additivity="false">
		<appender-ref ref="STDOUT" />
	</logger>
</configuration>
```
项目启动日志：
```
19:26:24.115 INFO  - Jsonview framework loaded the configuration file "/jsonview/jsonview-class.xml".
19:26:24.122 INFO  - Jsonview framework loaded the configuration file "/jsonview/jsonview-account.xml".
19:26:24.128 INFO  - Jsonview framework loaded the configuration file "/jsonview/jsonview-student.xml".
```
## **7. 整合Spring**
### **7.1. 使用方式**
maven
```
<dependency>
	<groupId>org.developframework</groupId>
	<artifactId>jsonview3-spring-support</artifactId>
	<version>${jsonview.version}</version>
</dependency>
```
### **7.2. 加载JsonviewFactory**
使用spring加载JsonviewFactory对象。
applicationContext.xml
```
<bean id="jsonviewFactory" class="org.developframework.jsonview.spring.JsonviewFactoryBean">
	<property name="configs">
		<set>
			<value>/jsonview/jsonview-student.xml</value>
			<value>/jsonview/jsonview-account.xml</value>
			<value>/jsonview/jsonview-class.xml</value>
		</set>
	</property>
</bean>
```
在配置文件较多时，上述方法比较麻烦，可以采用以下扫描配置文件包的方法缩减代码量。

使用`<jsonview:scan>`扫描文件包加载Jsonview configuration文件。
```
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:jsonview="http://www.developframework.org/schema/jsonview"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.1.xsd
		http://www.developframework.org/schema/jsonview http://www.developframework.org/schema/jsonview/jsonview-spring-namespace.xsd">	
	
	<jsonview:scan id="jsonviewFactory" locations="classpath:jsonview/*.xml" />
	
</beans>
```
此方法需要添加命名空间`xmlns:jsonview="http://www.developframework.org/schema/jsonview"`
`classpath:jsonview/*.xml`为通配加载jsonview文件夹下的所有配置文件。
