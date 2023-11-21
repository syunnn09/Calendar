package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao extends DaoBase {
	public void sample() {
		this.open();

		String sql = "SELECT * FROM sample;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.print(rs.getInt("id"));
				System.out.print(rs.getString("userName"));
				System.out.println(rs.getInt("age"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.close();
	}

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
