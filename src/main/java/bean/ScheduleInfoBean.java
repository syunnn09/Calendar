package bean;

import java.util.ArrayList;

public class ScheduleInfoBean {
	private ArrayList<ScheduleRecordBean> scheduleRecordArray = new ArrayList<ScheduleRecordBean>();

	public ScheduleInfoBean() { }

	public void setScheduleRecordBean(ArrayList<ScheduleRecordBean> list) {
		this.scheduleRecordArray = list;
	}

	public void add(ScheduleRecordBean bean) {
		this.scheduleRecordArray.add(bean);
	}

	public int getSize() {
		return this.scheduleRecordArray.size();
	}

	public ArrayList<ScheduleRecordBean> getScheduleRecordArray() {
		return this.scheduleRecordArray;
	}
}
