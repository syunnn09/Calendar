package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.ChatBean;
import bean.ChatInfoBean;

public class ChatDao extends DaoBase {
	private static final int PAGE_CONTENTS_COUNT = 30;

	public ChatInfoBean getChat(int roomId, int page) {
		return this.getChat(roomId, page, false);
	}

	public ChatInfoBean getChat(int roomId, int page, boolean isAdd) {
		try {
			this.open();
			String sql = "SELECT chat.*, name FROM chat INNER JOIN users ON chat.userId = users.userId WHERE roomId=? ORDER BY chatId DESC LIMIT ? OFFSET ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, roomId);
			ps.setInt(2, PAGE_CONTENTS_COUNT);
			ps.setInt(3, page * PAGE_CONTENTS_COUNT);
			ResultSet rs = ps.executeQuery();

			ChatInfoBean infoBean = new ChatInfoBean();
			while (rs.next()) {
				ChatBean bean = new ChatBean();
				bean.setChatId(rs.getInt("chatId"));
				bean.setUserId(rs.getInt("userId"));
				bean.setRoomId(roomId);
				bean.setMessage(rs.getString("message"));
				bean.setTimestamp(rs.getString("post_at"));
				bean.setUserName(rs.getString("name"));
				if (isAdd) {
					infoBean.add(bean);
				} else {
					infoBean.prepend(bean);
				}
			}
			return infoBean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setChat(int userId, int roomId, String message, String time) {
		try {
			this.open();
			String sql = "INSERT INTO chat(userId, roomId, message, post_at) VALUES(?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, roomId);
			ps.setString(3, message);
			ps.setString(4, time);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(ChatBean bean) {
		try {
			this.open();
			String sql = "INSERT INTO chat(roomId, userId, message, post_at) VALUES(?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, bean.getRoomId());
			ps.setInt(2, bean.getUserId());
			ps.setString(3, bean.getMessage());
			ps.setString(4, bean.getTimestamp());
			int result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getUserName(int userId) {
		try {
			this.open();
			String sql = "SELECT name FROM users WHERE userId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
