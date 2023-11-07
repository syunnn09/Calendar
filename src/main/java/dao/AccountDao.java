package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao extends DaoBase {
	
	public int login(String email,String password) {
		open();
		try {
			
			String sql = "SELECT id FROM user WHERE email = ? AND password = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, email);
			pStmt.setString(2, password);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				int userId = rs.getInt(1);
				return userId;
			} else { 
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
	}
}