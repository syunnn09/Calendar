package bean;

import java.io.Serializable;

public class GroupBean implements Serializable {
  private int AdminId;
  private String roomName; 
  private int roomId;
  private int userId;
  private int joinId;

  public GroupBean() {}

  public GroupBean(int roomId, String roomName) {
    this.roomId = roomId;
    this.roomName = roomName;
  }

  public GroupBean(int roomId,int userId) {
    this.roomId = roomId;
    this.userId = userId;
  }

  public GroupBean(int roomId,int userId,String roomName) {
    this.userId  =userId;
    this.roomName=roomName;
    this.roomId=roomId;
  }

	public int getAdminID() {
		return AdminId;
	}
	public void setAdminID(int adminID) {
		AdminId = adminID;
	}
	public String getRoomname() {
		return roomName;
	}
	public void setRoomname(String roomname) {
		this.roomName = roomname;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getJoinId() {
		return joinId;
	}
	public void setJoinId(int joinId) {
		this.joinId = joinId;
	}
}
