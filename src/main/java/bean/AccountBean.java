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
	
	/**
	 * AccountBeanメソッド
	 * 
	 * 指定されたuserIdとpasswordでAccountBeanオブジェクトを初期化します。
	 * 
	 * @param userId アカウントに関連付けられたユーザーID
	 * @param password アカウントに関連付けられたパスワード
	 * @author rerere
	 * @version 1.0.0
	 */
	public AccountBean(int userId, String password) {
		this.userId = userId;
		this.password = password;
	}
	/**
	 * AccountBeanメソッド
	 * 
	 * 指定されたuserName、password、userIdでAccountBeanオブジェクトを初期化する
	 * 
	 * @param userName アカウントに関連付けられたユーザー名
	 * @param password アカウントに関連付けられたパスワード
	 * @param userId アカウントに関連付けられたユーザーID
	 * @author rerere
	 * @version 1.0.0
	 */
	public AccountBean(String userName, String password, int userId) {
		this.userName = userName;
		this.password = password;
		this.userId = userId;
	}
	/**
	 * getUserIdメソッド
	 * 
	 * アカウントに関連付けられたユーザーIDを取得する
	 * 
	 * @return アカウントに関連付けられたユーザーID
	 * @author rerere
	 * @version 1.0.0
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * setUserIdメソッド
	 * 
	 * アカウントに関連付けられたユーザーIDを設定します。
	 * 
	 * @param userId 設定するユーザーID
	 * @author rerere
	 * @version 1.0.0
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * getPasswordメソッド
	 * 
	 * アカウントに関連付けられたパスワードを取得します。
	 * 
	 * @return アカウントに関連付けられたパスワード
	 * @author rerere
	 * @version 1.0.0
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * setPasswordメソッド
	 * 
	 * アカウントに関連付けられたパスワードを設定します。
	 * 
	 * @param password 設定するパスワード
	 * @author rerere
	 * @version 1.0.0
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * getUserNameメソッド
	 * 
	 * アカウントに関連付けられたユーザー名を取得します。
	 * 
	 * @return アカウントに関連付けられたユーザー名
	 * @author rerere
	 * @version 1.0.0
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * setUserNameメソッド
	 * 
	 * アカウントに関連付けられたユーザー名を設定します。
	 * 
	 * @param userName 設定するユーザー名
	 * @author rerere
	 * @version 1.0.0
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * getBirthdayメソッド
	 * 
	 * アカウントに関連付けられた誕生日を取得します。
	 * 
	 * @return アカウントに関連付けられた誕生日
	 * @author rerere
	 * @version 1.0.0
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @method setBirthday
	 * アカウントに関連付けられた誕生日を設定します。
	 * 
	 * @param birthday 設定する誕生日
	 * @author rerere
	 * @version 1.0.0
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * @method getEmail
	 * アカウントに関連付けられたメールアドレスを取得します。
	 * 
	 * @return アカウントに関連付けられたメールアドレス
	 * @author rerere
	 * @version 1.0.0
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @method getEmail
	 * アカウントに関連付けられたメールアドレスを取得します。
	 * 
	 * @return アカウントに関連付けられたメールアドレス
	 * @author rerere
	 * @version 1.0.0
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}