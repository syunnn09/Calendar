package bean;
import java.io.Serializable;
public class ScheduleRecordBean implements Serializable{
	
	private int scheduleId;
	private int roomId;
	private String startTime;
	private String endTime;
	private String name;
	private String detail;
	private String place;
	
	public ScheduleRecordBean() {}
	
	
	
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public int getScheduleId() {
		return scheduleId;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	
	
}