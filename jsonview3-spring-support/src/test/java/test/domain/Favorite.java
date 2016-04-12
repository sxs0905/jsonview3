package test.domain;

public class Favorite {

	private int userId;
	private String item;

	public Favorite(int userId, String item) {
		this.userId = userId;
		this.item = item;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Favorite [userId=" + userId + ", item=" + item + "]";
	}

}
