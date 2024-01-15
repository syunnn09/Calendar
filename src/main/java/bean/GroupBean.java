package bean;

import java.io.Serializable;

public class GroupBean implements Serializable {
  private int AdminId;
  private String roomName; 
  private int roomId;
  private int userId;
  private int joinId;
  private String userName;
  private String email;
  private int isAdmin;

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

  public int getAdminId() {
	return AdminId;
  }

  public void setAdminId(int adminId) {
	AdminId = adminId;
  }
	
  public String getRoomName() {
	return roomName;
  }
	
  public void setRoomName(String roomName) {
	this.roomName = roomName;
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
	
  public String getUserName() {
	return userName;
  }
	
  public void setUserName(String userName) {
	this.userName = userName;
  }
	
  public String getEmail() {
	return email;
  }
	
  public void setEmail(String email) {
	this.email = email;
  }
	
  public int getIsAdmin() {
	return isAdmin;
  }
	
  public void setIsAdmin(int isAdmin) {
	this.isAdmin = isAdmin;
  }
	  
	  
}
