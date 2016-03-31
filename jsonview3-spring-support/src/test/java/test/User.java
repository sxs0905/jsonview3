package test;

import java.util.Date;

public class User {

	private int id;
	private String name;
	private Date birthday;
	private Like[] likes = new Like[]{
			new Like("aaa"), new Like("bbb")
	};

	public User(int id, String name) {
		this.id = id;
		this.name = name;
		this.birthday = new Date();
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

	public Like[] getLikes() {
		return likes;
	}

	public void setLikes(Like[] likes) {
		this.likes = likes;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
