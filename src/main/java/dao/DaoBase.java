package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoBase {
	private final String JDBC_URL = "jdbc://";
	private final String USER = "";
	private final String PASS = "";

	public Connection conn = null;

	public void open() {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS)) {
			this.conn = conn;
		} catch (SQLException e) {
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
