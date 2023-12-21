package bean;
import java.io.Serializable;

public class AccountBean implements Serializable {
	
	private int userId;
	private String password;
	private String userName;
	private String birthday;
	private String email;

	public AccountBean() {}

	public AccountBean(String[] arr) {
		if (arr.length != 4) {
			return;
		}

		this.setUserName(arr[0]);
		this.setEmail(arr[1]);
		this.setPassword(arr[2]);
		this.setBirthday(arr[3]);
	}

	public AccountBean(int userId, String password) {
		this.userId = userId;
		this.password = password;
	}
	
	public AccountBean(String userName, String password, int userId) {
		this.userName = userName;
		this.password = password;
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
