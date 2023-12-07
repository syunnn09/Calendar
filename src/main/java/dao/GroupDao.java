package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.GroupBean;
import bean.GroupInfoBean;

public class GroupDao extends DaoBase {
	//グループ作成
	
	public void admin(GroupBean bean) {
		//データベースへ接続
		open();
	    try {
	    		
             //INSERT文を準備
			 String sql = "INSERT INTO joins(roomId, userId, isAdmin) VALUES (?, ?, 1)";
			 PreparedStatement pStmt = conn.prepareStatement(sql);
			 //INSERT文の中の「?」に使用する値をセットし、SQLを組み立て
			 //pStmt.setInt(1, groupBean.getUserId());	
			 pStmt.setInt(1, bean.getRoomId());
			 pStmt.setInt(2, bean.getUserId());
//			 pStmt.setInt(4, bean.getAdminID());
			
			//ResultSet rs = pStmt.executeQuery();
			//INSERT文を実行し、実行結果をresultに格納
			int result = pStmt.executeUpdate();


			//成功すると1が戻るので、1ではないときには失敗
			if (result != 1) {
				//return false;
			}

		} catch (SQLException e) {
			System.out.println("!! 管理者は追加(INSERT)されませんでした。");
			e.printStackTrace();
			//return false;

		}

		System.out.println("!! 管理者は正常に追加(INSERT)されました。");
		//return true;
	 }
	
	
	 public void insert(GroupBean bean) {
		//データベースへ接続
		open();
	    try {
	    		
             //INSERT文を準備
			 String sql = "INSERT INTO room(roomId , roomName) VALUES ( ?, ?)";
			 PreparedStatement pStmt = conn.prepareStatement(sql);
			 //INSERT文の中の「?」に使用する値をセットし、SQLを組み立て
			 //pStmt.setInt(1, groupBean.getUserId());	
			 pStmt.setInt(1, bean.getRoomId());		
			 pStmt.setString(2, bean.getRoomname());
            //pStmt.setInt(4, groupBean.getAdminID());
			
			//ResultSet rs = pStmt.executeQuery();
			//INSERT文を実行し、実行結果をresultに格納
			int result = pStmt.executeUpdate();


			//成功すると1が戻るので、1ではないときには失敗
			if (result != 1) {
				//return false;
			}

		} catch (SQLException e) {
			System.out.println("!! レコードは追加(INSERT)されませんでした。");
			e.printStackTrace();
			//return false;

		}

		System.out.println("!! レコードは正常に追加(INSERT)されました。");
		//return true;
	 }
	 
	 public void delete(GroupBean bean) {
			//データベースへ接続
	     open();
		 try {

			 //UPDATE文を準備
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
				System.out.println("!! レコードは削除(DELETE)されていません");
				e.printStackTrace();
				//return false;

			}
			System.out.println("!! レコードは正常に削除(DELETE)されました。");
			//return true;
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
			pStmt = conn.prepareStatement(sql);
			pStmt.executeUpdate();
			for (int i = 0; i < userIds.length; i++) {
				sql = "UPDATE joins SET isAdmin = 1 WHERE userId = ? AND roomId = ? ;";
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
