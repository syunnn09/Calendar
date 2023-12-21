package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.AccountBean;
import bean.AccountInfoBean;
import model.UserModel;

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

	public UserModel login(String email, String password) {
		open();
		try {
			String sql = "SELECT userId, isLogined FROM users WHERE email = ? AND password = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, email);
			pStmt.setString(2, password);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				int userId = rs.getInt(1);
				boolean isLogined = rs.getInt(2) != 0;
				UserModel user = new UserModel(userId, isLogined);
				return user;
			} else { 
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void addUser(AccountInfoBean beans) {
		this.open();

		try {
			for (AccountBean bean: beans.getAccountRecordArray()) {
				String sql = "INSERT INTO users(name, email, password, birthday) VALUES(?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, bean.getUserName());
				ps.setString(2, bean.getEmail());
				ps.setString(3, bean.getPassword());
				ps.setString(4, bean.getBirthday());
				ps.execute();
			}

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean changePassword(int userId, String password) {
		this.open();
		try {
			String sql = "UPDATE users SET password = ?, isLogined = 1 WHERE userId = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, password);
			pStmt.setInt(2, userId);
			int result = pStmt.executeUpdate();
			return result > 0;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void update(AccountBean account) {
		this.open();
		String sql = "UPDATE users SET name = ?, password = ? WHERE userId = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, account.getUserName());
			ps.setString(2, account.getPassword());
			ps.setInt(3, account.getUserId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getName(int userId) {
		this.open();
		String sql = "SELECT name FROM users WHERE userId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
			return "";
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}

	public int getIsLogined(String email) {
		this.open();

		try {
			String sql = "SELECT isLogined FROM users WHERE email=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
