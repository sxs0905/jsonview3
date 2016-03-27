package test;

import java.util.HashMap;
import java.util.Map;

public class User {

	private int id;
	private String name;
	private Like[] likes = new Like[] { new Like("aaa"), new Like("bbb") };
	private Map<String, String> map = new HashMap<String, String>();

	public User(int id, String name) {
		this.id = id;
		this.name = name;
		map.put("key1", "value1");
		map.put("key2", "value2");
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

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

}
