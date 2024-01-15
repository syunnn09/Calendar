package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoBase {
	private final String JDBC_URL = "jdbc:mariadb://localhost:54321/calendar?characterEncoding=UTF-8&serverTimezone=JST";
	private final String USER = "root";
	private final String PASS = "password";

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
}
