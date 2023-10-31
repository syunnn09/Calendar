package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoBase {
	private final String JDBC_URL = "jdbc:mySQL://localhost:3306/Calendar?characterEncoding=UTF-8&serverTimezone=JST";
	private final String USER = "root";
	private final String PASS = "mysql";

	public Connection conn = null;

	public void open() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try(Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS)) {
			this.conn = conn;
			System.out.println("mysql connected");
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
