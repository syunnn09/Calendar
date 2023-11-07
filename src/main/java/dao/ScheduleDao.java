package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ScheduleInfoBean;
import bean.ScheduleRecordBean;

public class ScheduleDao extends DaoBase{
	private ArrayList<ScheduleRecordBean> scheduleRecordArray = new ArrayList<ScheduleRecordBean>();
	
	//データベースに接続する情報
	public void getSchedule(int groupId){
		open();
		
		try {
			//スケジュールを取得する時のSQL文
			String spl = "SELECT * FROM schedule WHERE groupId = ?";
			PreparedStatement pStmt = conn.prepareStatement(spl);
			pStmt.setInt(1, groupId);
			
			//SQL文を実行して、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			
			
			while(rs.next()) {
				ScheduleRecordBean scheduleRecordBean = new ScheduleRecordBean();
				ScheduleInfoBean scheduleInfoBean = new ScheduleInfoBean();
				
				int scheduleId = rs.getInt("scheduleId");
				scheduleRecordBean.setScheduleId(1);
				
				int roomId = rs.getInt("roomId");
				scheduleRecordBean.setRoomId(1);
				
				String startTime = rs.getString("startTime");
				scheduleRecordBean.setStartTime("startTime");
				
				String endTime = rs.getString("endTime");
				scheduleRecordBean.setEndTime("endTime");
				
				String detail = rs.getString("detail");
				scheduleRecordBean.setDetail("detail");
				
				String place = rs.getString("place");
				scheduleRecordBean.setPlace("place");
				
//				scheduleInfoBean.add(ScheduleRecordBean);
				
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
//		return scheduleInfoBean;
		
		
	}
	
	
//	public int delete(int scheduleId) {
//		
//	}
//	
//	public ArrayList<ScheduleRecordBean> create(ScheduleRecordBean bean){
//		
//	}
//	
//	
//	
//	public void edit(ScheduleRecordBean bean) {
//		
//	}

	public void getAll(int userId) {
		this.open();
		String sql = "SELECT * FROM schedule WHERE roomId in (SELECT roomId FROM joins WHERE userId=?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			ScheduleInfoBean infoBean = new ScheduleInfoBean();

			while (rs.next()) {
				ScheduleRecordBean bean = new ScheduleRecordBean();
				bean.setScheduleId(rs.getInt("scheduleId"));
				bean.setTitle(rs.getString("title"));
				bean.setRoomId(rs.getInt("roomId"));
				bean.setStartDate(rs.getString("startDate"));
				bean.setEndDate(rs.getString("endDate"));
				bean.setPlace(rs.getString("place"));
				bean.setDetail(rs.getString("detail"));

				infoBean.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
