package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.ScheduleRecordBean;

public class ScheduleDao extends DaoBase {
	private PreparedStatement pStmt;

	public boolean createSchedule(ScheduleRecordBean srb) {
		try {
			open();
			String sql = "INSERT INTO schedules(roomId, startDate, endDate, title, detail, place) VALUES(?, ?, ?, ?, ?, ?);";
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, srb.getRoomId());
			pStmt.setString(2, srb.getStartDate());
			pStmt.setString(3, srb.getEndDate());
			pStmt.setString(4, srb.getTitle());
			pStmt.setString(5, srb.getDetail());
			pStmt.setString(6, srb.getPlace());
			pStmt.executeUpdate();
			close();

		} catch (SQLException e) {

		}
		return true;
	}
	
}
