package bean;

import java.io.Serializable;

public class ChatBean implements Serializable {
	private int chatId;
	private int roomId;
	private int userId;
	private String message;
	private String timestamp;
	private String userName;

	public ChatBean(int chatId, int roomId, int userId, String message, String timestamp) {
		this.chatId = chatId;
		this.roomId = roomId;
		this.userId = userId;
		this.message = message;
		this.timestamp = timestamp;
	}

	public ChatBean(int roomId, int userId, String message, String timestamp, String userName) {
		this.roomId = roomId;
		this.userId = userId;
		this.message = message;
		this.timestamp = timestamp;
		this.userName = userName;
	}

	public ChatBean(int room, int userId, String message) {
		this.roomId = room;
		this.userId = userId;
		this.message = message;
	}

	public ChatBean() { }

	public int getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
		this.chatId = chatId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isSystemUser() {
		return this.userId == 0;
	}

}
