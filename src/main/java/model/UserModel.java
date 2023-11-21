package model;

import java.io.Serializable;

public class UserModel implements Serializable {
	private int userId;
	private String password;
	private boolean isLogined;

	public UserModel(int userId, String password, boolean isLogined) {
		this.setUserId(userId);
		this.password = password;
		this.isLogined = isLogined;
	}

	public UserModel(int userId, boolean isLogined) {
		this.setUserId(userId);
		this.isLogined = isLogined;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean isLogined() {
		return isLogined;
	}

	public void setLogined(boolean isLogined) {
		this.isLogined = isLogined;
	}
}