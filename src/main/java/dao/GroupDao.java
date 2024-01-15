package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.GroupBean;
import bean.GroupInfoBean;

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
			String sql = "INSERT INTO room(roomName) VALUES (?)";
			PreparedStatement pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//INSERT文の中の「?」に使用する値をセットし、SQLを組み立て
			pStmt.setString(1, bean.getRoomname());
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

	public GroupInfoBean memberselect(int roomId) {
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
			open();
			for (int i = 0; i < userIds.length; i++) {
				String sql = "Insert Into joins(userId, roomId) values(?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt = conn.prepareStatement(sql);
				pStmt.setInt(1, userIds[i]);
				pStmt.setInt(2, roomId);
				pStmt.executeUpdate();
			}
			close();

		} catch (SQLException e) {

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

		}
	}

	public GroupInfoBean adminselect(int roomId) {
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
			System.out.println("adminselect失敗");
		}
		return null;
	}
 }
