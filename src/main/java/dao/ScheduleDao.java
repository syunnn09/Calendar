package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ScheduleRecordBean;

public class ScheduleDao extends DaoBase{
	private ArrayList<ScheduleRecordBean> scheduleRecordArray = new ArrayList<ScheduleRecordBean>();
	
	//データベースに接続する情報
	public ArrayList<ScheduleRecordBean> getSchedule(int groupId){
		opne();
		
		try(){
			//スケジュールを取得する時のSQL文
			String spl = "SELECT * FROM schedule WHERE groupId = ?";
			PreparedStatement pStmt = conn.prepareStatement(spl);
			pStmt.setInt(1, groupId);
			
			//SQL文を実行して、結果表を取得
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				ScheduleRecordBean scheduleRecordBean = new ScheduleRecordBean();
				ScheduleInfoBean scheduleInfoBean = new ScheduleInfoBean();
				
				int scheduleId = rs.getInt("scheduleId");
				scheduleRecordBean.setscheduleId("scheduleId");
				
				int roomId = rs.getInt("roomId");
				scheduleRecordBean.setRoomId("roomId");
				
				String startTime = rs.getString("startTime");
				scheduleRecordBean.setStartTime("startTime");
				
				String endTime = rs.getString("endTime");
				scheduleRecordBean.setEndTime("endTime");
				
				String detail = rs.getString("detail");
				scheduleRecordBean.setDetail("detail");
				
				String place = rs.getString("place");
				scheduleRecordBean.setPlace("place");
				
				scheduleInfoBean.add(ScheduleRecordBean);
				
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return scheduleInfoBean;
		
		
	}
	
	
	public int delete(int scheduleId) {
		
	}
	
	public ArrayList<ScheduleRecordBean> create(ScheduleRecordBean bean){
		
	}
	
	
	
	public void edit(ScheduleRecordBean bean) {
		
	}
}
