package bean;

import java.io.Serializable;

import javax.websocket.Session;

public class ChatSessionBean implements Serializable {
	private Session session;
	private int userId;
	private String userName;

	public ChatSessionBean() { }

	public ChatSessionBean(Session session, int userId, String userName) {
		this.session = session;
		this.userId = userId;
		this.userName = userName;
	}

	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
