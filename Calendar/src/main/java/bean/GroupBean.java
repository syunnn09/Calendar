package bean;

import java.io.Serializable;

/**
 * GroupBeanクラス
 * group機能に必要なフィールド変数のgetter、setter
 * 
 * @author　ootubo,fukumori
 * @version 1.0.0
 */
public class GroupBean implements Serializable {
	private int AdminId;
	private String roomName;
	private int roomId;
	private int userId;
	private int joinId;
	private String userName;
	private String Email;
	private int IsAdmin;
	private String color;
	private boolean isNeedNotify;

	/**
	 * グループBeanの新しいインスタンスを生成します。
	 *
	 * @author ootubo,fukumori
	 * @param roomId   グループのID
	 * @param roomName グループの名前
	 * @version 1.0.0
	 */
	public GroupBean(int roomId, String roomName) {
		this.roomId = roomId;
		this.roomName = roomName;
	}

	/**
	 * グループBeanの新しいインスタンスを生成します。
	 *
	 * @author ootubo,fukumori
	 * @param roomId   グループのID
	 * @param roomName グループの名前
	 * @param color    グループの色
	 * @version 1.0.0
	 */
	public GroupBean(int roomId, String roomName, String color) {
		this.roomId = roomId;
		this.roomName = roomName;
		this.color = color;
	}

	/**
	 * グループBeanの新しいインスタンスを生成します。
	 *
	 * @author ootubo,fukumori
	 * @version 1.0.0
	 */
	public GroupBean() {
	}

	/**
	 * グループBeanの新しいインスタンスを生成します。
	 *
	 * @author ootubo,fukumori
	 * @param userId ユーザーのID
	 * @version 1.0.0
	 */
	public GroupBean(int userId) {
		this.userId = userId;
	}

	/**
	 * グループBeanの新しいインスタンスを生成します。
	 *
	 * @author ootubo,fukumori
	 * @param roomId グループのID
	 * @param userId ユーザーのID
	 * @version 1.0.0
	 */
	public GroupBean(int roomId, int userId) {
		this.roomId = roomId;
		this.userId = userId;
	}

	/**
	 * グループBeanの新しいインスタンスを生成します。
	 *
	 * @author ootubo,fukumori
	 * @param roomId   グループのID
	 * @param userId   ユーザーのID
	 * @param roomName グループの名前
	 * @version 1.0.0
	 */
	public GroupBean(int roomId, int userId, String roomName) {
		this.roomId = roomId;
		this.userId = userId;
		this.roomName = roomName;
	}

	/**
	 * このグループの管理者のIDを取得します。
	 *
	 * @author ootubo,fukumori
	 * @return このグループの管理者のID
	 * @version 1.0.0
	 */
	public int getAdminID() {
		return AdminId;
	}

	/**
	 * このグループの管理者のIDを設定します。
	 *
	 * @author ootubo,fukumori
	 * @param adminID このグループの管理者のID
	 * @version 1.0.0
	 */
	public void setAdminID(int adminID) {
		AdminId = adminID;
	}

	/**
	 * このグループの名前を取得します。
	 *
	 * @author ootubo,fukumori
	 * @return このグループの名前
	 * @version 1.0.0
	 */
	public String getRoomname() {
		return roomName;
	}

	/**
	 * このグループの名前を設定します。
	 *
	 * @author ootubo,fukumori
	 * @param roomname 設定するグループの名前
	 * @version 1.0.0
	 */
	public void setRoomname(String roomname) {
		this.roomName = roomname;
	}

	/**
	 * このグループのIDを取得します。
	 *
	 * @author ootubo,fukumori
	 * @return このグループのID
	 * @version 1.0.0
	
	 */
	public int getRoomId() {
		return roomId;
	}

	/**
	 * このグループのIDを設定します。
	 *
	 *	@author ootubo,fukumori
	 * @param roomId 設定するグループのID
	 * @version 1.0.0
	 */
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	/**
	 * このユーザーのIDを取得します。
	 *
	 * @author ootubo,fukumori
	 * @return このユーザーのID
	 * @version 1.0.0
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * このユーザーのIDを設定します。
	 *
	 * @author ootubo,fukumori
	 * @param userId 設定するユーザーのID
	 * @version 1.0.0
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 参加IDを取得します。
	 *
	 * @author ootubo,fukumori
	 * @return 参加ID
	 * @version 1.0.0
	 */
	public int getJoinId() {
		return joinId;
	}

	/**
	 * 参加IDを設定します。
	 *
	 * @author ootubo,fukumori
	 * @param joinId 設定する参加ID
	 * @version 1.0.0
	 */
	public void setJoinId(int joinId) {
		this.joinId = joinId;
	}

	/**
	 * ユーザーの名前を取得します。
	 *
	 * @author ootubo,fukumori
	 * @return ユーザーの名前
	 * @version 1.0.0
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * ユーザーの名前を設定します。
	 *
	 * @author ootubo,fukumori
	 * @param userName 設定するユーザーの名前
	 * @version 1.0.0
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * ユーザーのメールアドレスを取得します。
	 *
	 * @author ootubo,fukumori
	 * @return メールアドレス
	 * @version 1.0.0
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * ユーザーのメールアドレスを設定します。
	 *
	 * @author ootubo,fukumori
	 * @param email 設定するメールアドレス
	 * @version 1.0.0
	 */
	public void setEmail(String email) {
		Email = email;
	}

	/**
	 * 管理者権限の有無を取得します。
	 *
	 * @author ootubo,fukumori
	 * @return 管理者権限の有無
	 * @version 1.0.0
	 */
	public int getIsAdmin() {
		return IsAdmin;
	}

	/**
	 * 管理者権限の有無を設定します。
	 *
	 * @author ootubo,fukumori
	 * @param isAdmin 管理者権限の有無を設定する値
	 * @version 1.0.0
	 */
	public void setIsAdmin(int isAdmin) {
		IsAdmin = isAdmin;
	}

	/**
	 * グループの色を取得します。
	 *
	 * @author ootubo,fukumori
	 * @return グループの色
	 * @version 1.0.0
	 */
	public String getColor() {
		return color;
	}

	/**
	 * グループの色を設定します。
	 *
	 * @author ootubo,fukumori
	 * @param color 設定するグループの色
	 * @version 1.0.0
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * 通知の必要性を取得します。
	 *
	 * @author ootubo,fukumori
	 * @return 通知の必要性
	 * @version 1.0.0
	 */
	public boolean isNeedNotify() {
		return isNeedNotify;
	}

	/**
	 * 通知の必要性を設定します。
	 *
	 * @author ootubo,fukumori
	 * @param isNeedNotify 通知の必要性を設定する値
	 * @version 1.0.0
	 */
	public void setNeedNotify(boolean isNeedNotify) {
		this.isNeedNotify = isNeedNotify;
	}
}
