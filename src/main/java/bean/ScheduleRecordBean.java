package bean;
import java.io.Serializable;
public class ScheduleRecordBean implements Serializable{
	
	private int scheduleId;
	private int roomId;
	private String startDate;
	private String endDate;
	private String title;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startTime) {
		this.startDate = startTime;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endTime) {
		this.endDate = endTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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

	@Override
	public String toString() {
		return this.title;
	}
}