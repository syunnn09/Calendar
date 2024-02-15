package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.GroupBean;
import bean.GroupInfoBean;
import servlet.chat.ChatSessionManager;

public class GroupDao extends DaoBase {
	//グループ作成
	public void admin(GroupBean bean) {
		//データベースへ接続
		open();
		try {
			String sql = "INSERT INTO joins(roomId, userId, isAdmin) VALUES (?, ?, 1)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, bean.getRoomId());
			pStmt.setInt(2, bean.getUserId());
			int result = pStmt.executeUpdate();
			//成功すると1が戻るので、1ではないときには失敗
			if (result != 1) {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int insert(GroupBean bean) {
		//データベースへ接続
		open();
		try {
			//INSERT文を準備
			String sql = "INSERT INTO room(roomName, color) VALUES (?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//INSERT文の中の「?」に使用する値をセットし、SQLを組み立て
			pStmt.setString(1, bean.getRoomname());
			pStmt.setString(2, bean.getColor());
			pStmt.executeQuery();
			ResultSet rs = pStmt.getGeneratedKeys();
			int roomId = -1;
			if (rs.next()) {
				roomId = rs.getInt(1);
			}
			return roomId;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public void delete(GroupBean bean) {
		//データベースへ接続
		open();
		try {
			String sql = "DELETE FROM joins WHERE roomId = ? AND userId = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//UPDATE文の中の「 ? 」に使用する値をセットし、SQLを組み立て
			pStmt.setInt(1, bean.getRoomId());
			pStmt.setInt(2, bean.getUserId());
			//INSERT文を実行し、実行結果をresultに格納
			int result = pStmt.executeUpdate();
			//成功すると1が戻るので、1ではないときには失敗
			if (result != 1) {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 }


	public GroupInfoBean notmemSelect(int roomId) {
		try {
			open();
			String sql = "SELECT userId, name, email FROM users WHERE NOT EXISTS(SELECT * FROM joins WHERE joins.userId=users.userId AND joins.roomId=?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, roomId);
			ResultSet rs = pStmt.executeQuery();
			close();
			GroupInfoBean gib = new GroupInfoBean();
			while (rs.next()) {
				GroupBean gb = new GroupBean();
				gb.setUserId(rs.getInt("userId"));
				gb.setUserName(rs.getString("name"));
				gb.setEmail(rs.getString("email"));
				gb.setRoomId(roomId);
				gib.addGroup(gb);
			}
			return gib;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("memberselect失敗");
		}
		return null;
	}

  	public void insert(int[] userIds, int roomId) {
		try {
			String users = "";
			open();
			for (int i = 0; i < userIds.length; i++) {
				String sql = "Insert Into joins(userId, roomId) values(?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt = conn.prepareStatement(sql);
				pStmt.setInt(1, userIds[i]);
				pStmt.setInt(2, roomId);
				users += getUserName(userIds[i]);
				if (i < userIds.length - 1) {
				    users += ", ";
				}
				pStmt.executeUpdate();
			}
			close();
			String message = "[メンバー:" + users + " が追加されました。]";
			ChatSessionManager.getManager().sendSystemMessage(roomId, message);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void grant(int[] userIds, int roomId) {
		try {
			open();
			String sql = "UPDATE joins SET isAdmin = 0";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt = conn.prepareStatement(sql);
			pStmt.executeUpdate();
			for (int i = 0; i < userIds.length; i++) {
				sql = "UPDATE joins SET isAdmin = 1 WHERE userId = ? AND roomId = ? ;";
				pStmt = conn.prepareStatement(sql);
				pStmt = conn.prepareStatement(sql);
				pStmt.setInt(1, userIds[i]);
				pStmt.setInt(2, roomId);
				pStmt.executeUpdate();
			}
			close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public GroupInfoBean yesmemSelect(int roomId) {
		try {
			open();
			String sql = "SELECT users.userId, name, email, isAdmin FROM users,joins WHERE users.userId=joins.userId AND roomId=?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, roomId);
			ResultSet rs = pStmt.executeQuery();
			close();
			GroupInfoBean gib = new GroupInfoBean();
			while (rs.next()) {
				GroupBean gb = new GroupBean();
				gb.setUserId(rs.getInt("userId"));
				gb.setUserName(rs.getString("name"));
				gb.setEmail(rs.getString("email"));
				gb.setIsAdmin(rs.getInt("isAdmin"));
				gb.setRoomId(roomId);
				gib.addGroup(gb);
			}
			return gib;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int adminCheck(int userId, int roomId) {
		try {
			open();
			String sql = "SELECT EXISTS(select userId from joins where userId=? and roomId=? and isAdmin=1)as admin";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);
			pStmt.setInt(2, roomId);
			ResultSet rs=pStmt.executeQuery();
			rs.first();
			close();
			return rs.getInt("admin");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void setLastsaw(int userId, int roomId) {
		try {
			int maxChatId = getReadChatId(userId, roomId);
			String sql;
			PreparedStatement ps;
			this.open();
			if (maxChatId == -1) {
				sql = "INSERT INTO lastsaw SELECT ?, ?, (SELECT MAX(chatId) FROM chat WHERE roomId = ?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, userId);
				ps.setInt(2, roomId);
				ps.setInt(3, roomId);
			} else {
				sql = "UPDATE lastsaw SET lastSawChatId = (SELECT MAX(chatId) FROM chat WHERE roomId = ?) WHERE roomId = ? AND userId = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, roomId);
				ps.setInt(2, roomId);
				ps.setInt(3, userId);
			}
			ps.executeUpdate();
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getMaxChatId(int roomId) {
		int maxChatId = -1;
		try {
			this.open();
			String sql = "SELECT MAX(chatId) FROM chat WHERE roomId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, roomId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				maxChatId = rs.getInt(1);
			}
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxChatId;
	}

	public int getReadChatId(int userId, int roomId) {
		int maxChatId = -1;
		try {
			this.open();
			String sql = "SELECT lastSawChatId FROM lastsaw WHERE userId = ? AND roomId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, roomId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				maxChatId = rs.getInt(1);
			}
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxChatId;
	}

	public GroupInfoBean getAllGroup(int userId) {
		try {
			open();
			String sql = "SELECT roomId, roomName, color FROM room WHERE roomId IN (SELECT roomId FROM joins WHERE userId = ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			GroupInfoBean groupInfoBean = new GroupInfoBean();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				GroupBean bean = new GroupBean();
				int roomId = rs.getInt(1);
				String roomName = rs.getString(2);
				String color = rs.getString(3);
				int maxChatId = getMaxChatId(roomId);
				int readChatId = getReadChatId(userId, roomId);
				bean.setRoomId(roomId);
				bean.setRoomname(roomName);
				bean.setColor(color);
				bean.setNeedNotify(maxChatId > readChatId);
				groupInfoBean.addGroup(bean);
			}
			close();
			return groupInfoBean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}