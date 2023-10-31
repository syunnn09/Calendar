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
}
