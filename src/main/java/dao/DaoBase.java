package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoBase {
//	private final String JDBC_URL = "jdbc:mariadb://localhost:3306/calendar?characterEncoding=UTF-8&serverTimezone=JST";
//	private final String USER = "root";
//	private final String PASS = "mysql";
	private final String HOST = "106DP17-046";
	private final String PORT = "3306";
	private final String JDBC_URL = "jdbc:mariadb://" + HOST + ":" + PORT + "/calendar?characterEncoding=UTF-8&serverTimezone=JST";
	private final String USER = "R4A1XX";
	private final String PASS = "passwd";

	public Connection conn = null;

	public void open() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			this.conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		if (conn == null) {
			return;
		}

		try {
			conn.close();
		} catch (SQLException e) {
			return;
		}
	}
	
	public String getUserName(int userId) {
		try {
			this.open();
			String sql = "SELECT name FROM users WHERE userId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
