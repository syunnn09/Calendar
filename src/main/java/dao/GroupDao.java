package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.GroupBean;

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
				System.out.println("!! 管理者は正常に追加(INSERT)されました。");
			}
		} catch (SQLException e) {
			System.out.println("!! 管理者は追加(INSERT)されませんでした。");
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
				System.out.println("!! レコードは正常に追加(INSERT)されました。");
				roomId = rs.getInt(1);
			} else {
				System.out.println("!! レコードは追加(INSERT)されませんでした。");
			}

			return roomId;
		} catch (SQLException e) {
			System.out.println("!! レコードは追加(INSERT)されませんでした。");
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
				System.out.println("!! レコードは正常に削除(DELETE)されました。");
			}
		} catch (SQLException e) {
			System.out.println("!! レコードは削除(DELETE)されていません");
			e.printStackTrace();
		}
	}

}
