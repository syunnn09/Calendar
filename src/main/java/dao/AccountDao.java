package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.UserModel;

public class AccountDao extends DaoBase {
	
	public UserModel login(String userId,String password) {
		 UserModel usermodel = null;
	
		try {
			
			String sql = "SELECT ID,PASSWORD";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, getUserId());
			pStmt.setString(2, getPassWord());
			
			ResultSet rs = pStmt.executeQuery();
			
			
			if (rs.next()) {
				String userId = rs.getString("USER_ID");
				String userId = rs.getString("USER_ID");
				
			}
			
		}
	}
}
