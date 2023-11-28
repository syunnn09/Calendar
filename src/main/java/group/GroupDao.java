package group;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DaoBase;

public class GroupDao extends DaoBase {
	private PreparedStatement pStmt;
	
	public void insert(int[] userIds,int roomId) {
		try {
			open();
			for(int i=0;i<userIds.length;i++) {				
				String sql="Insert Into joins(userId, roomId) values(?, ?)";
				pStmt=conn.prepareStatement(sql);
				pStmt.setInt(1, userIds[i]);
				pStmt.setInt(2, roomId);
				pStmt.executeUpdate();
			}
			close();
		
		}catch(SQLException e) {
			
		}
		
	}
	
	public void grant(int[] userIds,int roomId) {
		try {
			open();
			String sql ="UPDATE joins SET isAdmin = 0";
			pStmt=conn.prepareStatement(sql);
			pStmt.executeUpdate();			
			for(int i=0;i<userIds.length;i++) {				
				sql="UPDATE joins SET isAdmin = 1 WHERE userId = ? AND roomId = ? ;";
				pStmt=conn.prepareStatement(sql);
				pStmt.setInt(1, userIds[i]);
				pStmt.setInt(2, roomId);
				pStmt.executeUpdate();
			}
			close();
			
		}catch(SQLException e) {
			
		}
	}
	
	public ResultSet adminselect(int roomId) {
		try {			
			open();
			String sql = "SELECT users.userId, name, email, isAdmin FROM users,joins WHERE users.userId=joins.userId AND roomId=?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, roomId);
			ResultSet rs = pStmt.executeQuery(sql);
			close();
			return rs;
		}catch(SQLException e) {
			System.out.println("失敗");
		}
		return null;
	}
	public ResultSet memberselect() {
		try {			
			open();
			String sql = "SELECT userId, name, email FROM users WHERE NOT EXISTS(SELECT * FROM joins WHERE joins.userId=users.userId);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery(sql);
			close();
			return rs;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("失敗");
		}
		return null;
	}
}
