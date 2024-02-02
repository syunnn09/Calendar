package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.ScheduleInfoBean;
import bean.ScheduleRecordBean;
import servlet.chat.ChatSessionManager;

public class ScheduleDao extends DaoBase {
	ChatDao chatDao = new ChatDao();
	//データベースに接続する情報
	public ScheduleInfoBean getScheduleArray(int groupId){
		open();
		
		try {
			//スケジュールを取得する時のSQL文
			String spl = "SELECT *, color FROM schedules INNER JOIN room ON schedules.roomId = room.roomId WHERE schedules.roomId = ?";
			PreparedStatement pStmt = conn.prepareStatement(spl);
			pStmt.setInt(1, groupId);
			
			ScheduleInfoBean scheduleInfoBean = new ScheduleInfoBean();
			//SQL文を実行して、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				ScheduleRecordBean bean = new ScheduleRecordBean();

				bean.setScheduleId(rs.getInt("scheduleId"));
				bean.setTitle(rs.getString("title"));
				bean.setRoomId(rs.getInt("roomId"));
				bean.setStartDate(rs.getString("startDate"));
				bean.setEndDate(rs.getString("endDate"));
				bean.setPlace(rs.getString("place"));
				bean.setDetail(rs.getString("detail"));
				bean.setColor(rs.getString("color"));
				
				scheduleInfoBean.add(bean);
			}
			return scheduleInfoBean;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public ScheduleRecordBean getSchedule(int scheduleId) {
		this.open();
		String sql = "SELECT *, color FROM schedules INNER JOIN room ON schedules.roomId = room.roomId WHERE scheduleId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, scheduleId);

			ResultSet rs = ps.executeQuery();
			ScheduleRecordBean bean = new ScheduleRecordBean();

			if (rs.next()) {
				bean.setScheduleId(rs.getInt("scheduleId"));
				bean.setTitle(rs.getString("title"));
				bean.setRoomId(rs.getInt("roomId"));
				bean.setStartDate(rs.getString("startDate"));
				bean.setEndDate(rs.getString("endDate"));
				bean.setPlace(rs.getString("place"));
				bean.setDetail(rs.getString("detail"));
				bean.setColor(rs.getString("color"));
			}
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ScheduleInfoBean getAll(int userId) {
		this.open();
		String sql = "SELECT *, color FROM schedules INNER JOIN room ON schedules.roomId = room.roomId WHERE schedules.roomId in (SELECT roomId FROM joins WHERE userId=?)";
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
				bean.setColor(rs.getString("color"));

				infoBean.add(bean);
			}
			this.close();

			return infoBean;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean createSchedule(ScheduleRecordBean srb) {
		try {
			open();
			String sql = "INSERT INTO schedules(roomId, startDate, endDate, title, detail, place) VALUES(?, ?, ?, ?, ?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, srb.getRoomId());
			pStmt.setString(2, srb.getStartDate());
			pStmt.setString(3, srb.getEndDate());
			pStmt.setString(4, srb.getTitle());
			pStmt.setString(5, srb.getDetail());
			pStmt.setString(6, srb.getPlace());
			pStmt.executeUpdate();
			close();

			String message = "[予定: " + srb.getTitle() + " が作成されました。]";
			ChatSessionManager.getManager().sendSystemMessage(srb.getRoomId(), message);
		} catch (SQLException e) {

		}
		return true;
	}

	public void delete(ScheduleRecordBean bean) {
		try {
			this.open();
			String sql = "DELETE FROM schedules WHERE scheduleId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, bean.getScheduleId());
			ps.executeUpdate();
			this.close();

			String message = "[予定: " + bean.getTitle() + " が削除されました。]";
			ChatSessionManager.getManager().sendSystemMessage(bean.getRoomId(), message);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void edit(ScheduleRecordBean bean) {
		try {
			this.open();
			String sql = "UPDATE schedules SET roomId = ?, startDate = ?, endDate = ?, title = ?, detail = ?, place = ? WHERE scheduleId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, bean.getRoomId());
			ps.setString(2, bean.getStartDate());
			ps.setString(3, bean.getEndDate());
			ps.setString(4, bean.getTitle());
			ps.setString(5, bean.getDetail());
			ps.setString(6, bean.getPlace());
			ps.setInt(7, bean.getScheduleId());
			ps.executeUpdate();
			this.close();

			String message = "[予定: " + bean.getTitle() + " が更新されました。]";
			ChatSessionManager.getManager().sendSystemMessage(bean.getRoomId(), message);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
