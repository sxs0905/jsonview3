package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

	private int id;
	private String name;
	private Date birthday;
	private List<Like> likes = new ArrayList<>();

	public User(int id, String name) {
		this.id = id;
		this.name = name;
		this.birthday = new Date();
		likes.add(new Like("aaa"));
		likes.add(new Like("bbb"));
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

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
