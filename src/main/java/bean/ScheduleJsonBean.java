package bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScheduleJsonBean {
	@JsonProperty("scheduleId")
	private int scheduleId;

	@JsonProperty("roomId")
	private int roomId;

	@JsonProperty("startDate")
	private String startDate;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("title")
	private String title;

	@JsonProperty("detail")
	private String detail;

	@JsonProperty("place")
	private String place;

	public ScheduleJsonBean(ScheduleRecordBean bean) {
		this.scheduleId = bean.getScheduleId();
		this.roomId = bean.getRoomId();
		this.startDate = bean.getStartDate();
		this.endDate = bean.getEndDate();
		this.title = bean.getTitle();
		this.detail = bean.getDetail();
		this.place = bean.getPlace();
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
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

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
}
