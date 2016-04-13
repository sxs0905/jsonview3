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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
