package dao;

import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;

import bean.GroupBean;	
		
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
	 
 }

		
		
	 
		
	
 






